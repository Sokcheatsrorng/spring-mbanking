package co.istad.sokcheatmbankingapi.features.user;

import co.istad.sokcheatmbankingapi.base.BasedMessage;
import co.istad.sokcheatmbankingapi.features.user.dto.*;
import co.istad.sokcheatmbankingapi.status.DisableUser;
import co.istad.sokcheatmbankingapi.status.EnableUser;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void userCreateRequest(UserCreateRequest request);
    UserResponse updateUser(String uuid, UserUpdateRequest request);
    UserDetailResponse getUserByUuid(String uuid);

    void changePassword(UserChangePasswordRequest request);
    void updateProfile(UserUpdateProfileRequest request,String uuid);

    BasedMessage blockByUuid(String uuid);

    void deleteUser(String uuid);

    EnableUser enableUser(String uuid);

    DisableUser disableUser(String uuid);

    Page<UserResponse> findList(int page, int limit);
    String updateProfileImage(String uuid,String mediaName);

}








