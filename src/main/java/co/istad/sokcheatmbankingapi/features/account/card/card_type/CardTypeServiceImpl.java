package co.istad.sokcheatmbankingapi.features.account.card.card_type;

import co.istad.sokcheatmbankingapi.domain.CardType;
import co.istad.sokcheatmbankingapi.features.account.card.card_type.dto.CardTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CardTypeServiceImpl implements CardTypeService {
    private final CardTypeRepository cardTypeRepository;

    @Override
    public List<CardType> findList() {
        List<CardType> cardType = cardTypeRepository.findAll();
        return cardType;
    }
    @Override
    public CardTypeResponse  findByName(String name) {
        CardType cardType = cardTypeRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "CardType not found"));
        return new CardTypeResponse(
                cardType.getName(),
                cardType.getIsDeleted()
        );
    }
}
