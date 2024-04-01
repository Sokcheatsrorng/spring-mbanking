package co.istad.sokcheatmbankingapi.features.user;

import co.istad.sokcheatmbankingapi.base.BaseMessage;
import co.istad.sokcheatmbankingapi.domain.User;
import co.istad.sokcheatmbankingapi.features.user.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void userCreateRequest(@Valid @RequestBody UserCreateRequest userRequest) {
        userService.userCreateRequest(userRequest);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void changePassword(@Valid @RequestBody UserChangePasswordRequest userRequest) {
        userService.changePassword(userRequest);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{uuid}")
    public void updateProfile(@Valid @RequestBody UserUpdateProfileRequest request,
                              @PathVariable String uuid) {
        userService.updateProfile(request,uuid);
    }
    @PatchMapping("/{uuid}")
    public UserResponse updateUser(@PathVariable String uuid,
                                   @Valid @RequestBody UserUpdateRequest request){
        return userService.updateUser(uuid,request);
    }
    @GetMapping
    public Page<UserResponse> findList(@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0"
    )int page,
     @RequestParam(
             value = "limit",
             required = false,
             defaultValue = "2"
     ) int limit                                   ){
         return userService.findList(page,limit);
    }
    @GetMapping("/{uuid}")
    public UserDetailResponse getUsersByUuid(@PathVariable String uuid){
        return userService.getUserByUuid(uuid);
    }
    @PutMapping("/{uuid}/block")
    public BaseMessage updateBlock(@PathVariable String uuid){
        return userService.blockByUuid(uuid);
    }
    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@Valid @PathVariable String uuid){
        userService.deleteUser(uuid);
    }
    @PutMapping("/{uuid}/enable")
    public void enableUser(@Valid @PathVariable String uuid){
         userService.enableUser(uuid);
    }
    @PutMapping("/{uuid}/disable")
    public void disableUser(@Valid @PathVariable String uuid){
        userService.disableUser(uuid);
    }

}
