package co.istad.sokcheatmbankingapi.features.transaction.dto;

import co.istad.sokcheatmbankingapi.features.account.dto.AccountResponse;
import co.istad.sokcheatmbankingapi.features.account.dto.AccountSnippetResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
     AccountSnippetResponse owner,
     AccountSnippetResponse transferReceiver,
     BigDecimal amount,
     Boolean status,
     String remark,
     String transactionType,
     LocalDateTime transactionAt


) {
}
