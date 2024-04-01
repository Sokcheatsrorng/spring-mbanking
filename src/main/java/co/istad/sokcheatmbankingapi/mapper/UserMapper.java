package co.istad.sokcheatmbankingapi.mapper;

import co.istad.sokcheatmbankingapi.domain.User;
import co.istad.sokcheatmbankingapi.features.user.dto.UserCreateRequest;
import co.istad.sokcheatmbankingapi.features.user.dto.UserDetailResponse;
import co.istad.sokcheatmbankingapi.features.user.dto.UserResponse;
import co.istad.sokcheatmbankingapi.features.user.dto.UserUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

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
    List<UserResponse> toUserResponseList(List<User> users);
}
