package co.istad.sokcheatmbankingapi.features.card_type;

import co.istad.sokcheatmbankingapi.domain.CardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardTypeRepository extends JpaRepository<CardType,Integer> {
    Optional<CardType> findByName(String name);
}
