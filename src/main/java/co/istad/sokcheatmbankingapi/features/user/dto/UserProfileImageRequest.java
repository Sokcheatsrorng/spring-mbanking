package co.istad.sokcheatmbankingapi.features.user.dto;

import jakarta.validation.constraints.NotNull;

public record UserProfileImageRequest(
        @NotNull(message = "Profile Image is required")
        String profileImage
) {
}
