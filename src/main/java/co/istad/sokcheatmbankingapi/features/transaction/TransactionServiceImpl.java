package co.istad.sokcheatmbankingapi.features.transaction;

import co.istad.sokcheatmbankingapi.domain.Account;
import co.istad.sokcheatmbankingapi.domain.Transaction;
import co.istad.sokcheatmbankingapi.features.account.AccountRepository;
import co.istad.sokcheatmbankingapi.features.account_type.AccountTypeRepository;
import co.istad.sokcheatmbankingapi.features.transaction.dto.TransactionCreateRequest;
import co.istad.sokcheatmbankingapi.features.transaction.dto.TransactionResponse;
import co.istad.sokcheatmbankingapi.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;
    @Transactional
    @Override
    public TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest) {
//        log.info("transfer(transactionCreateRequest):{}",createRequest);
        //check account
        Account owner = accountRepository.findByActNo(transactionCreateRequest.ownerActNo())
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Account owner has not found!"
                        ));
        // check transferReceiver
        Account transferReceiver = accountRepository.findByActNo(transactionCreateRequest.transferReceiverActNo())
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Account Receiver is not found!"
                        ));

        // check amount of transfer
        if(owner.getBalance().doubleValue() < transactionCreateRequest.amount().doubleValue()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Insufficient balance!"
            );
        }
        // check transferLimit of owner
        if(transactionCreateRequest.amount().doubleValue() >= owner.getTransferLimit().doubleValue()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Transaction has been over the transfer limit!"
            );
        }
        // logic to substract amount from owner
        owner.setBalance(owner.getBalance().subtract(transactionCreateRequest.amount()));
        // logic to add amount to receiver
        transferReceiver.setBalance(transferReceiver.getBalance().add(transactionCreateRequest.amount()));
        // save owner and receiver
        Transaction transaction = transactionMapper.fromTransactionRequest(transactionCreateRequest);
        transaction.setOwner(owner);
        transaction.setTransferReceiver(transferReceiver);
        transaction.setTransactionType("TRANSFER");
        transaction.setStatus(true);
        transaction.setTransactionAt(LocalDateTime.now());
        transaction = transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(transaction);
    }

    @Override
    public Page<TransactionResponse> transactionHistory(int page, int size, String sort, String transactionType) {
        // Validate page and size
        if (page < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page number must be greater than or equal to zero"
            );
        }
        if (size < 1) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page size must be greater than or equal to one"
            );
        }

        Sort.Direction direction = Sort.Direction.ASC;
        if (sort != null && sort.equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }
        // Define sorting by date
        Sort sorted;
        if ("type".equalsIgnoreCase(transactionType)) {
            sorted = Sort.by(direction, "transactionType");
        } else {
            sorted = Sort.by(direction, "transactionAt");
        }

        // Create page request
        PageRequest pageRequest = PageRequest.of(page, size, sorted);

        // Fetch transactions from repository based on transaction type
        Page<Transaction> transactionsPage;

        if ("transfer".equalsIgnoreCase(transactionType)) {
            transactionsPage = transactionRepository.findByTransactionType("TRANSFER", pageRequest);
        } else if ("payment".equalsIgnoreCase(transactionType)) {
            transactionsPage = transactionRepository.findByTransactionType("PAYMENT", pageRequest);
        } else {
            transactionsPage = transactionRepository.findAll(pageRequest);
        }

        // Map transactions to TransactionResponse and return page
        return transactionsPage.map(transactionMapper::toTransactionResponse);
    }
}
