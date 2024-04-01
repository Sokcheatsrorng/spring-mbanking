package co.istad.sokcheatmbankingapi.features.account_type;

import co.istad.sokcheatmbankingapi.domain.AccountType;
import co.istad.sokcheatmbankingapi.features.account_type.dto.AccountTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/account-types")
public class AccountTypeController {
    private final AccountTypeService accountTypeService;
    @GetMapping
    public List<AccountTypeResponse> getAllAccountTypes() {
        return accountTypeService.findList();
    }
    @GetMapping("/{alias}")
    public AccountTypeResponse getAccountTypeByAlias(@PathVariable String alias) {
        return accountTypeService.findByAlias(alias);
    }
}
