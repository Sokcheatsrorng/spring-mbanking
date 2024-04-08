package co.istad.sokcheatmbankingapi.features.account.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AccountLimitTransferRequest(
        @NotNull(message="TransferLimit is required")
        BigDecimal transferLimit
) {
}
