package co.istad.sokcheatmbankingapi.features.account;

import co.istad.sokcheatmbankingapi.domain.Account;
import co.istad.sokcheatmbankingapi.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
    Optional<UserAccount> findByAccount(Account account);
}
