package co.istad.sokcheatmbankingapi.features.account_type.dto;

import jakarta.persistence.Column;

public record AccountTypeResponse(
        @Column(unique = true, nullable = false, length = 100)
        String name,
        @Column(unique = true, nullable = false)
        String alias,

        @Column(columnDefinition = "TEXT")
        String description


) {
}
