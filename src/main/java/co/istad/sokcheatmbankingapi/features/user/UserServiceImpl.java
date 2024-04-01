package co.istad.sokcheatmbankingapi.features.user;

import co.istad.sokcheatmbankingapi.base.BaseMessage;
import co.istad.sokcheatmbankingapi.domain.Role;
import co.istad.sokcheatmbankingapi.domain.User;
import co.istad.sokcheatmbankingapi.features.user.dto.*;
import co.istad.sokcheatmbankingapi.mapper.UserMapper;
import co.istad.sokcheatmbankingapi.status.DisableUser;
import co.istad.sokcheatmbankingapi.status.EnableUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
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
        // create dynamic roles from client
        userCreateRequest.roles().stream().forEach(r->{
            Role newRole = roleRespository.findByName(r.name())
                    .orElseThrow(
                             ()->
                                new ResponseStatusException(
                                         HttpStatus.NOT_FOUND,
                                         "Role "+r.name()+" not found!"
                                 )
                         );
            roles.add(newRole);
        });
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

    }

    @Override
    public UserResponse updateUser(String uuid, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User has not been found!"
                        )

                );

        log.info("Before User: ",user.getName());
        log.info("Before User: ",user.getGender());
        log.info("Before User: ",user.getPhoneNumber());
        log.info("Before User: ",user.getNationalCardId());

        userMapper.fromUpdateUser(userUpdateRequest,user);
        log.info("After User: ",user.getName());
        log.info("After User: ",user.getGender());
        log.info("After User: ",user.getPhoneNumber());
        log.info("After User: ",user.getNationalCardId());

        user = userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserDetailResponse getUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User uuid has been not found!"
                        )
                );
        return userMapper.toUserDetailsResponse(user);

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
    @Transactional
    @Override
    public BaseMessage blockByUuid(String uuid) {
        if(!userRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "User is not blocked!"
            );
        }
        userRepository.blockByUuid(uuid);
        return new BaseMessage("user has been blocked");
    }

    @Override
    public void deleteUser(String uuid) {
         User user = userRepository.findByUuid(uuid)
                 .orElseThrow(()->
                         new ResponseStatusException(
                                 HttpStatus.NOT_FOUND,
                                 "User uuid has been not found!"
                         ));
         userRepository.delete(user);
    }
    @Override
    @Transactional
    public EnableUser enableUser(String uuid) {
        if(!userRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "User is enable"
            );
        }
        userRepository.enableByUuid(uuid);
        return new EnableUser("User is enable");
    }

    @Override
    @Transactional
    public DisableUser disableUser(String uuid) {
        if(!userRepository.existsByUuid(uuid)){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "User is enable"
            );
        }
        userRepository.disableByUuid(uuid);
        return new DisableUser("User is disable");
    }
    @Override
    public Page<UserResponse> findList(int page,int limit) {
        //Create PageRequest object
        PageRequest pageRequest = PageRequest.of(page,limit);
        //Invoke findAll(pageRequest)
        Page<User> users = userRepository.findAll(pageRequest);
//       List<User> users = userRepository.findAll();
        return users.map(userMapper::toUserResponse);
    }
}
