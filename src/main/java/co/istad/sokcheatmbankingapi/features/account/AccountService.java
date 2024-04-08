package co.istad.sokcheatmbankingapi.features.account;

import co.istad.sokcheatmbankingapi.features.account.dto.AccountCreateRequest;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountRenameRequest;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountResponse;
import org.springframework.data.domain.Page;

public interface AccountService {
    void createNew(AccountCreateRequest accountCreateRequest);

    AccountResponse findByActNo(String actNo);

    Page<AccountResponse> findAll(int page, int limit);

    AccountResponse renameByActNo(String actNo, AccountRenameRequest accountRenameRequest);
}
