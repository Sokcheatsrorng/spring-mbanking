package co.istad.sokcheatmbankingapi.mapper;

import co.istad.sokcheatmbankingapi.domain.AccountType;
import co.istad.sokcheatmbankingapi.domain.User;
import co.istad.sokcheatmbankingapi.features.account_type.dto.AccountTypeResponse;
import co.istad.sokcheatmbankingapi.features.user.dto.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {
    AccountTypeResponse toAccountTypeResponse(AccountType accountType);
    List<AccountTypeResponse> toAccountTypeReponseList(List<AccountType> accountType);

}
