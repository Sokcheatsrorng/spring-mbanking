package co.istad.sokcheatmbankingapi.mapper;

import co.istad.sokcheatmbankingapi.domain.User;
import co.istad.sokcheatmbankingapi.features.user.dto.UserCreateRequest;
import co.istad.sokcheatmbankingapi.features.user.dto.UserDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // sourceType = UserCreateRequest(Parameter)
    // Target = User (ReturnType)
    User fromUserCreateRequest(UserCreateRequest userCreateRequest);

    void fromUserCreateRequest2(@MappingTarget User user, UserCreateRequest userCreateRequest);

    UserDetailResponse userDetailResponse(User user);
}
