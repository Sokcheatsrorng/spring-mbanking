package co.istad.sokcheatmbankingapi.features.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserChangePasswordRequest(
        @NotNull
        @NotBlank
        String phoneNumber,
        @NotNull
        @NotBlank
        String oldPwd,
        @NotNull
        @NotBlank
        String newPwd,
        @NotNull
        @NotBlank
        String confirmedPwd

) {
}
