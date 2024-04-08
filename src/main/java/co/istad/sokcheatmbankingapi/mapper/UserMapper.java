package co.istad.sokcheatmbankingapi.mapper;

import co.istad.sokcheatmbankingapi.domain.Account;
import co.istad.sokcheatmbankingapi.domain.User;
import co.istad.sokcheatmbankingapi.domain.UserAccount;
import co.istad.sokcheatmbankingapi.features.user.dto.UserCreateRequest;
import co.istad.sokcheatmbankingapi.features.user.dto.UserDetailResponse;
import co.istad.sokcheatmbankingapi.features.user.dto.UserResponse;
import co.istad.sokcheatmbankingapi.features.user.dto.UserUpdateRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // sourceType = UserCreateRequest(Parameter)
    // Target = User (ReturnType)
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);

    void fromUserCreateRequest2(@MappingTarget User user, UserCreateRequest userCreateRequest);

    UserDetailResponse userDetailResponse(User user);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateUser(UserUpdateRequest userUpdateRequest, @MappingTarget User user);


    UserDetailResponse toUserDetailsResponse(User user);
    UserResponse toUserResponse(User user);

    UserAccount toUserAccount(User user);
    User fromUserAccount(Account account);
    @Named("mapUserResponse")
    default UserResponse mapUserResponse(List<UserAccount> userAccountList){
        return toUserResponse(userAccountList.get(0).getUser());
    }


    List<UserResponse> toUserResponseList(List<User> users);
    // map user from userAccount

}
