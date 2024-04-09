package co.istad.sokcheatmbankingapi.features.transaction.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionCreateRequest(
        @NotBlank(message = "Account Number is required")
        String ownerActNo,

        @NotBlank(message="Transfer Account no of receiver is required")
        String transferReceiverActNo,

        @NotNull(message="Amount is required")
        @Positive
        BigDecimal amount,
        String remark

) {
}
