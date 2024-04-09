package co.istad.sokcheatmbankingapi.mapper;

import co.istad.sokcheatmbankingapi.domain.Account;
import co.istad.sokcheatmbankingapi.domain.User;
import co.istad.sokcheatmbankingapi.domain.UserAccount;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountCreateRequest;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountResponse;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountSnippetResponse;
import co.istad.sokcheatmbankingapi.features.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring" ,uses={
        UserMapper.class,
        AccountTypeMapper.class

})
public interface AccountMapper {
  Account fromCreateAccountRequest(AccountCreateRequest request);

  // tell the map struct that use my logic (custom map struct)
  @Mapping(source = "userAccountList", target="user",
          qualifiedByName = "mapUserResponse")
  AccountResponse toAccountResponse(Account account);
  // we can use map struct / mapper stay in resource

  AccountSnippetResponse toAccountSnippetResponse(Account account);

}



