package co.istad.sokcheatmbankingapi.features.account;

import co.istad.sokcheatmbankingapi.features.account.dto.AccountCreateRequest;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountLimitTransferRequest;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountRenameRequest;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {
    void createNew(AccountCreateRequest accountCreateRequest);

    AccountResponse findByActNo(String actNo);

    Page<AccountResponse> findAll(int page, int limit);

    AccountResponse renameByActNo(String actNo, AccountRenameRequest accountRenameRequest);
    void hideAccount(String actNo);
    Page<AccountResponse> findAllAccounts(int page, int size);

    AccountResponse updateTransferLimit(String actNo, AccountLimitTransferRequest accountLimitTransferRequest);
}
