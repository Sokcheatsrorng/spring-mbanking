package co.istad.sokcheatmbankingapi.features.user.dto;

import java.time.LocalDate;

public record UserUpdateRequest(
        String name,
        String gender,
        LocalDate dob,
        String studentIdCard
) {
}
