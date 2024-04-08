package co.istad.sokcheatmbankingapi.features.account;

import co.istad.sokcheatmbankingapi.features.account.dto.AccountCreateRequest;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountLimitTransferRequest;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountRenameRequest;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountResponse;
import co.istad.sokcheatmbankingapi.features.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    @PutMapping("/{actNo}/rename")
    AccountResponse renameByActNo(@PathVariable String actNo,
                        @RequestBody AccountRenameRequest accountRenameRequest){
        return accountService.renameByActNo(actNo,accountRenameRequest);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@Valid @RequestBody
                              AccountCreateRequest accountCreateRequest) {
        accountService.createNew(accountCreateRequest);
    }
    @GetMapping("/{actNo}")
    AccountResponse findByActNo(@PathVariable String actNo) {
        return accountService.findByActNo(actNo);
    }
    @GetMapping
    public Page<AccountResponse> findList(@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0"
    )int page,
                                       @RequestParam(
                                               value = "size",
                                               required = false,
                                               defaultValue = "25"
                                       ) int limit                                   ){
        return accountService.findAllAccounts(page,limit);
    }

    @PutMapping("/{actNo}/hide")
    void hideAccount(@PathVariable String actNo){
        accountService.hideAccount(actNo);
    }
    @PutMapping("/{actNo}/transferLimit")
    AccountResponse updateTransferLimit(@PathVariable String actNo,
                             @Valid @RequestBody AccountLimitTransferRequest accountLimitTransferRequest){
         return accountService.updateTransferLimit(actNo,accountLimitTransferRequest);

    }
}
