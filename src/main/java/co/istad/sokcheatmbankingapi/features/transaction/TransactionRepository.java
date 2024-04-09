package co.istad.sokcheatmbankingapi.features.transaction;

import co.istad.sokcheatmbankingapi.domain.Transaction;
import co.istad.sokcheatmbankingapi.features.transaction.dto.TransactionResponse;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.support.TransactionTemplate;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
//    @Query(" SELECT tra FROM Transaction AS tra ORDER BY tra.transactionAt ASC")
//    String findAllByDate(String date);
     Page<Transaction> findByTransactionType(String transactionType, Pageable pageable);
}
