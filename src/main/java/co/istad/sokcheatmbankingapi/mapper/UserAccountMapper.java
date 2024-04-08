package co.istad.sokcheatmbankingapi.mapper;

import co.istad.sokcheatmbankingapi.domain.User;
import co.istad.sokcheatmbankingapi.domain.UserAccount;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountResponse;
import co.istad.sokcheatmbankingapi.features.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    UserResponse toUserResponse(UserAccount userAccount);
}
