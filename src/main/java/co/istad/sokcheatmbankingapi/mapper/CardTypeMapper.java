package co.istad.sokcheatmbankingapi.mapper;

import co.istad.sokcheatmbankingapi.domain.CardType;
import co.istad.sokcheatmbankingapi.features.card_type.dto.CardTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {
    CardTypeResponse toCardTypeResponse(CardType cardType);
    List<CardTypeResponse> toCardTypeResponseList(List<CardType> cardTypeList);

}
