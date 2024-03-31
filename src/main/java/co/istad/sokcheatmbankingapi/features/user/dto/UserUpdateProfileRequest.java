package co.istad.sokcheatmbankingapi.features.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UserUpdateProfileRequest(
        @Column(length = 100)
         String cityOrProvince,
        @Column(length = 100)
        String khanOrDistrict,

        @Column(length = 100)
        String sangkatOrCommune,

        @Column(length = 100)
        String village,

        @Column(length = 100)
        String street,

        @Column(length = 100)
        String employeeType,

        @Column(length = 100)
        String position,

        @Column(length = 100)
        String companyName,

        @Column(length = 100)
        String mainSourceOfIncome,
        BigDecimal monthlyIncomeRange
) {
}
