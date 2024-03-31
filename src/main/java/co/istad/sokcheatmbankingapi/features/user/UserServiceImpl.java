package co.istad.sokcheatmbankingapi.features.user;

import co.istad.sokcheatmbankingapi.domain.Role;
import co.istad.sokcheatmbankingapi.domain.User;
import co.istad.sokcheatmbankingapi.features.user.dto.UserChangePasswordRequest;
import co.istad.sokcheatmbankingapi.features.user.dto.UserCreateRequest;
import co.istad.sokcheatmbankingapi.features.user.dto.UserUpdateProfileRequest;
import co.istad.sokcheatmbankingapi.mapper.UserMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRespository roleRespository;
    private final UserMapper userMapper;
    @Override
    public void userCreateRequest(UserCreateRequest userCreateRequest) {
        if(userRepository.existsByPhoneNumber(userCreateRequest.phoneNumber())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number has already been existed!"
            );
        }
        if(userRepository.existsByNationalCardId(userCreateRequest.nationalCardId())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "NationalCard ID has already been existed!"
            );
        }
        if(userRepository.existsByStudentIdCard(userCreateRequest.studentId())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student Card ID  has already been existed!"
            );
        }
        if (!userCreateRequest.password()
                .equals(userCreateRequest.confirmedPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password doesn't match!"
            );
        }

        User user = userMapper.fromUserCreateRequest(userCreateRequest);
        user.setUuid(UUID.randomUUID().toString());
        user.setCreatedAt(LocalDateTime.now());
        user.setProfileImage("avatar.jpg");
        user.setIsBlocked(false);
        user.setIsDeleted(false);

        List<Role> roles = new ArrayList<>();
        Role userRole = roleRespository.findByName("USER")
                        .orElseThrow(
                                ()->
                                   new ResponseStatusException(
                                            HttpStatus.NOT_FOUND,
                                            "Role USER not found!"
                                    )
                        );
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

    }
    @Override
    public void changePassword(UserChangePasswordRequest changePasswordRequest) {

       User user = userRepository.findByPhoneNumber(changePasswordRequest.phoneNumber())
               .orElseThrow(()->
                   new ResponseStatusException(
                           HttpStatus.NOT_FOUND,
                           "User uuid has been not found!"
                   )
               );
        if (!user.getPassword()
                .equals(changePasswordRequest.oldPwd())

        ) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Old password doesn't match!"
            );
        }
        if(!changePasswordRequest.newPwd()
                .equals(changePasswordRequest.confirmedPwd())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The password doesn't match!"
            );
        }
        user.setPassword(changePasswordRequest.newPwd());
        userRepository.save(user);

    }

    @Override
    public void updateProfile(UserUpdateProfileRequest updateProfileRequest,String uuid) {
        User user = userRepository.findByUuid(uuid)
               .orElseThrow(()->
                   new ResponseStatusException(
                           HttpStatus.NOT_FOUND,
                           "User uuid has been not found!"
                   )
               );

                user.setCityOrProvince(updateProfileRequest.cityOrProvince());
                user.setSangkatOrCommune(updateProfileRequest.sangkatOrCommune());
                user.setKhanOrDistrict(updateProfileRequest.khanOrDistrict());
                user.setVillage(updateProfileRequest.village());
                user.setVillage(updateProfileRequest.village());
                user.setEmployeeType(updateProfileRequest.employeeType());
                user.setStreet(updateProfileRequest.street());
                user.setPosition(updateProfileRequest.position());
                user.setMainSourceOfIncome(updateProfileRequest.mainSourceOfIncome());
                user.setMonthlyIncomeRange(updateProfileRequest.monthlyIncomeRange());
        userRepository.save(user);
    }

}
