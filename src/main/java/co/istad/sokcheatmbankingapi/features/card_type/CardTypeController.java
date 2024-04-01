package co.istad.sokcheatmbankingapi.features.card_type;

import co.istad.sokcheatmbankingapi.domain.CardType;
import co.istad.sokcheatmbankingapi.features.card_type.dto.CardTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/cards/card-types")
@RequiredArgsConstructor
public class CardTypeController {
    private final CardTypeService cardTypeService;
    @GetMapping
    public List<CardTypeResponse> findList()
    {
        return cardTypeService.findList();
    }
    @GetMapping("/{name}")
    public CardTypeResponse findCardTypeByName(@PathVariable String name){
        return cardTypeService.findByName(name.toUpperCase());
    }
}
