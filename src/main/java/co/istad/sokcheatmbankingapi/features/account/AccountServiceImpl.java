package co.istad.sokcheatmbankingapi.features.account;

import co.istad.sokcheatmbankingapi.domain.Account;
import co.istad.sokcheatmbankingapi.domain.AccountType;
import co.istad.sokcheatmbankingapi.domain.User;
import co.istad.sokcheatmbankingapi.domain.UserAccount;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountCreateRequest;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountRenameRequest;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountResponse;
import co.istad.sokcheatmbankingapi.features.account_type.AccountTypeRepository;
import co.istad.sokcheatmbankingapi.features.account_type.dto.AccountTypeResponse;
import co.istad.sokcheatmbankingapi.features.user.UserRepository;
import co.istad.sokcheatmbankingapi.features.user.dto.UserDetailResponse;
import co.istad.sokcheatmbankingapi.features.user.dto.UserResponse;
import co.istad.sokcheatmbankingapi.mapper.AccountMapper;
import co.istad.sokcheatmbankingapi.mapper.AccountTypeMapper;
import co.istad.sokcheatmbankingapi.mapper.UserAccountMapper;
import co.istad.sokcheatmbankingapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    private final UserAccountRepository userAccountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;
    private final UserAccountMapper userAccountMapper;
    private final UserMapper userMapper;
    @Override
    public void createNew(AccountCreateRequest accountCreateRequest) {
        //check account type
        AccountType accountType =  accountTypeRepository.findByAlias(accountCreateRequest.accountTypeAlias())
                .orElseThrow(
                        ()->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Account type alias has been not found!"
                                )
                );
        //check user by uuid
        User user = userRepository.findByUuid(accountCreateRequest.userUuid())
                .orElseThrow(
                        ()->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "User uuid has been not found!"
                                )
                );
        // map account dto to account entity
        Account account = accountMapper.fromCreateAccountRequest(accountCreateRequest);
        account.setAccountType(accountType);
        account.setActName(user.getName());
        account.setActNo("123456787");
        account.setTransferLimit(BigDecimal.valueOf(5000));
        account.setIsHidden(false);

        UserAccount userAccount = new UserAccount();
        userAccount.setAccount(account);
        userAccount.setUser(user);
        userAccount.setIsDeleted(false);
        userAccount.setIsBlocked(false);
        userAccount.setCreatedAt(LocalDateTime.now());

       userAccountRepository.save(userAccount);

    }

    @Override
    public AccountResponse findByActNo(String actNo) {
        // Check if account Number exists
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Account Number has not been found!"
                        )
                );
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public Page<AccountResponse> findAll(int page, int limit) {
        //Create PageRequest object
        PageRequest pageRequest = PageRequest.of(page,limit);
        //Invoke findAll(pageRequest)
        Page<Account> users = accountRepository.findAll(pageRequest);
//       List<User> users = userRepository.findAll();
        return users.map(accountMapper::toAccountResponse);
    }
    @Override
    public AccountResponse renameByActNo(String actNo, AccountRenameRequest accountRenameRequest) {
        // check actNo if exists
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Account Number has not been found!"
                        ));
        // check account alias
        if(account.getAlias() != null && account.getAlias().equals(accountRenameRequest.newName())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "New name must not the same as existing name"
            );

        }
        account.setAlias(accountRenameRequest.newName());
        account= accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }

}
