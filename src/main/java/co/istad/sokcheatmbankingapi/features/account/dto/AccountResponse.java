package co.istad.sokcheatmbankingapi.features.account.dto;

import co.istad.sokcheatmbankingapi.features.account_type.dto.AccountTypeResponse;
import co.istad.sokcheatmbankingapi.features.user.dto.UserResponse;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

public record AccountResponse(
        @NotNull(message = "Alias is required")
        String alias,
        String actNo,
        String actName,
        BigDecimal balance,
        AccountTypeResponse accountType,
        BigDecimal transferLimit,
        UserResponse user
) {
}
