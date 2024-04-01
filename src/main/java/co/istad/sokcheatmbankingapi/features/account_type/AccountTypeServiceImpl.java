package co.istad.sokcheatmbankingapi.features.account_type;

import co.istad.sokcheatmbankingapi.domain.AccountType;
import co.istad.sokcheatmbankingapi.features.account_type.dto.AccountTypeResponse;
import co.istad.sokcheatmbankingapi.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;
    @Override
    public List<AccountTypeResponse> findList() {
        List<AccountType> accountTypes = accountTypeRepository.findAll();
        return accountTypeMapper.toAccountTypeReponseList(accountTypes);
    }
    @Override
    public AccountTypeResponse findByAlias(String alias) {
        AccountType accountType = accountTypeRepository.findByAlias(alias)
                .orElseThrow(()-> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "AccountType could not found!"
                        )
                );

        return accountTypeMapper.toAccountTypeResponse(accountType);
    }
}
