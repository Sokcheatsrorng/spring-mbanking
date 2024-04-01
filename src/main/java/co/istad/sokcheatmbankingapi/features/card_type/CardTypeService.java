package co.istad.sokcheatmbankingapi.features.card_type;

import co.istad.sokcheatmbankingapi.domain.CardType;
import co.istad.sokcheatmbankingapi.features.card_type.dto.CardTypeResponse;

import java.util.List;

public interface CardTypeService {
    List<CardTypeResponse> findList();
   CardTypeResponse findByName(String name);
}
