package co.istad.sokcheatmbankingapi.features.account.card.card_type.dto;

import jakarta.persistence.Column;

public record CardTypeResponse(
        @Column(unique = true, nullable = false, length = 100)
        String name,

        Boolean isDeleted
) {
}
