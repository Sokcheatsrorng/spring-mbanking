package co.istad.sokcheatmbankingapi.features.card_type.dto;

import jakarta.persistence.Column;

public record CardTypeResponse(
        @Column(unique = true, nullable = false, length = 100)
        String name
) {
}
