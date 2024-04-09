package co.istad.sokcheatmbankingapi.features.transaction;

import co.istad.sokcheatmbankingapi.domain.Transaction;
import co.istad.sokcheatmbankingapi.features.transaction.dto.TransactionCreateRequest;
import co.istad.sokcheatmbankingapi.features.transaction.dto.TransactionResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface TransactionService {
    TransactionResponse transfer(TransactionCreateRequest createRequest);

    Page<TransactionResponse> transactionHistory(int page, int size, String sort, String transactionType);
}
