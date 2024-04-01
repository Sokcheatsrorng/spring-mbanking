package co.istad.sokcheatmbankingapi.features.card_type;

import co.istad.sokcheatmbankingapi.domain.CardType;
import co.istad.sokcheatmbankingapi.features.card_type.dto.CardTypeResponse;
import co.istad.sokcheatmbankingapi.mapper.CardTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CardTypeServiceImpl implements CardTypeService {
    private final CardTypeRepository cardTypeRepository;
    private final CardTypeMapper cardTypeMapper;

    @Override
    public List<CardTypeResponse> findList() {
        List<CardType> cardType = cardTypeRepository.findAll();
        return cardTypeMapper.toCardTypeResponseList(cardType);
    }
    @Override
    public CardTypeResponse findByName(String name) {
        CardType cardType = cardTypeRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "CardType not found"));
        return cardTypeMapper.toCardTypeResponse(cardType);
    }
}
