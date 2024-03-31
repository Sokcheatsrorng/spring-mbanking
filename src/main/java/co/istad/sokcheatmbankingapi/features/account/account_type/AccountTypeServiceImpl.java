package co.istad.sokcheatmbankingapi.features.account.account_type;

import co.istad.sokcheatmbankingapi.domain.AccountType;
import co.istad.sokcheatmbankingapi.features.account.account_type.dto.AccountTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;
    @Override
    public List<AccountType> findList() {
        List<AccountType> accountTypes = accountTypeRepository.findAll();
        return accountTypes;
    }

    @Override
    public AccountTypeResponse findByAlias(String alias) {
        AccountType accountType = accountTypeRepository.findByAlias(alias)
                .orElseThrow(()-> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "AccountType could not found!"
                        )
                );

        return new AccountTypeResponse(
                accountType.getName(),
                accountType.getDescription(),
                accountType.getIsDeleted()
        );
    }
}
