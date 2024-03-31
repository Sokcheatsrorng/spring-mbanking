package co.istad.sokcheatmbankingapi.features.account.account_type;

import co.istad.sokcheatmbankingapi.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountTypeRepository extends JpaRepository<AccountType,Integer> {
    Optional<AccountType> findByAlias(String alias);
}
