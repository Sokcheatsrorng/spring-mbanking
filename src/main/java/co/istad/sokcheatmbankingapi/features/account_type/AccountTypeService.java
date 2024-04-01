package co.istad.sokcheatmbankingapi.features.account_type;

import co.istad.sokcheatmbankingapi.domain.AccountType;
import co.istad.sokcheatmbankingapi.features.account_type.dto.AccountTypeResponse;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeResponse> findList();
    AccountTypeResponse findByAlias(String alias);

}
