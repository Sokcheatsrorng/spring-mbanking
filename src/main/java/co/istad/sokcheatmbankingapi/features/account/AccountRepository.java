package co.istad.sokcheatmbankingapi.features.account;

import co.istad.sokcheatmbankingapi.domain.Account;
import co.istad.sokcheatmbankingapi.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
  Optional<Account> findByActNo(String ActNo);
  boolean existsByActNo(String ActNo);
  @Modifying
  @Query("""
   UPDATE Account AS a
   SET a.isHidden = TRUE 
   where a.actNo = ?1
   """)
  void hideAccountByActNo(String ActNo);

}
