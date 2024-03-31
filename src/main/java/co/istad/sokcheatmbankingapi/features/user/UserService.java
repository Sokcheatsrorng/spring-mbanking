package co.istad.sokcheatmbankingapi.features.user;

import co.istad.sokcheatmbankingapi.features.user.dto.UserChangePasswordRequest;
import co.istad.sokcheatmbankingapi.features.user.dto.UserCreateRequest;
import co.istad.sokcheatmbankingapi.features.user.dto.UserUpdateProfileRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void userCreateRequest(UserCreateRequest request);
    void changePassword(UserChangePasswordRequest request);
    void updateProfile(UserUpdateProfileRequest request,String uuid);
}
