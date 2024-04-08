package co.istad.sokcheatmbankingapi.features.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountRenameRequest(
        @NotBlank(message="New name is required")
        @Size(message="New name must be less than or equal to 18.")
        String newName
) {
}
