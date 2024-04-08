package co.istad.sokcheatmbankingapi.features.user.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record UserCreateRequest(
        @NotNull(message = "Pin is required")
        @Max(value = 9999,message = "Pin must be 4 digits")
        @Positive(message = "Pin must be positive")
        Integer pin,
        @NotBlank(message = "Phone number is required")
        @Size(max=20,message = "Phone number is less than 20 characters")
        String phoneNumber,
        @NotBlank(message = "Name is required")
        @Size(max=40,message ="Name is less than 40 characters" )
        String name,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank (message = "ConfirmedPassword is required")
        String confirmedPassword,
        @NotBlank(message = "Gender is required")
        @Size(max=6, message="Gender must less than 6 characters")
        String gender,
        @NotNull(message = "Date of Birth is required")
        LocalDate dob,
        @NotBlank(message = "NationalCard ID is required")
        @Size(max=20,message = "NationalCard ID is less than 20 characters")
        String nationalCardId,
        @NotBlank(message = "Student ID is required")
        @Size(max=20,message = "Student ID is less than 20 characters")
        String studentId,
        @NotEmpty
        List<RoleRequest> roles


) {
}
