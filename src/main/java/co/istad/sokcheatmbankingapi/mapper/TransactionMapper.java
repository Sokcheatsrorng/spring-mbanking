package co.istad.sokcheatmbankingapi.mapper;

import co.istad.sokcheatmbankingapi.domain.Transaction;
import co.istad.sokcheatmbankingapi.features.transaction.dto.TransactionCreateRequest;
import co.istad.sokcheatmbankingapi.features.transaction.dto.TransactionResponse;
import lombok.Builder;
import org.mapstruct.Mapper;

import java.awt.*;

@Mapper(componentModel="spring")
public interface TransactionMapper {
    TransactionResponse toTransactionResponse(Transaction transaction);

    Transaction fromTransactionRequest(TransactionCreateRequest transactionCreateRequest);
}

