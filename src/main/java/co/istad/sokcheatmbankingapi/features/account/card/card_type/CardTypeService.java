package co.istad.sokcheatmbankingapi.features.account.card.card_type;

import co.istad.sokcheatmbankingapi.domain.CardType;
import co.istad.sokcheatmbankingapi.features.account.card.card_type.dto.CardTypeResponse;

import java.util.List;

public interface CardTypeService {
    List<CardType> findList();
   CardTypeResponse findByName(String name);
}
