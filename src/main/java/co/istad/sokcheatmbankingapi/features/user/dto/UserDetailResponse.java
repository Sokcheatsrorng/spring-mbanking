package co.istad.sokcheatmbankingapi.features.user.dto;

import co.istad.sokcheatmbankingapi.domain.Role;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record UserDetailResponse(
        String uuid,
        String name,
        String profileImage,
        String gender,
        LocalDate dob,
        String cityOrProvince,
        String khanOrDistrict,
        String village,
        String street,
        String employeeType,
        String position,
        String companyName,
        String mainSourceOfIncome,
        BigDecimal monthlyIncomeRange,
        String studentIDCard,
        List<RoleNameResponse> roles
) {
}
