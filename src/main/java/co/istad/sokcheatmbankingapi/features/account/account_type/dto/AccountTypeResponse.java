package co.istad.sokcheatmbankingapi.features.account.account_type.dto;

import jakarta.persistence.Column;

public record AccountTypeResponse(
        @Column(unique = true, nullable = false, length = 100)
        String name,

        @Column(columnDefinition = "TEXT")
        String description,

        Boolean isDeleted
) {
}
