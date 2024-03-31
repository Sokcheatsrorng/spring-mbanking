package co.istad.sokcheatmbankingapi.features.user;

import co.istad.sokcheatmbankingapi.features.user.dto.UserChangePasswordRequest;
import co.istad.sokcheatmbankingapi.features.user.dto.UserCreateRequest;
import co.istad.sokcheatmbankingapi.features.user.dto.UserUpdateProfileRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
