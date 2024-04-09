package co.istad.sokcheatmbankingapi.features.transaction;

import co.istad.sokcheatmbankingapi.features.account.dto.AccountResponse;
import co.istad.sokcheatmbankingapi.features.transaction.dto.TransactionCreateRequest;
import co.istad.sokcheatmbankingapi.features.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping
    TransactionResponse createTransfer(@Valid @RequestBody TransactionCreateRequest transactionRequest) {
        return transactionService.transfer(transactionRequest);
    }
//    @GetMapping
//    TransactionResponse sortTransactionByDate(@PathVariable LocalDateTime date,
//                                              @PathVariable String transaction_type){
//        return transactionService.sortTransactionByDate(date,transaction_type);
//    }
    @GetMapping
    public Page<TransactionResponse> getTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String transaction_type) {

        return transactionService.transactionHistory(page, size, sort,transaction_type);
    }
}
