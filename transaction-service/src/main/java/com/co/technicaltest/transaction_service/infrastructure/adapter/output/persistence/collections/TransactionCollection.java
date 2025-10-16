package com.co.technicaltest.transaction_service.infrastructure.adapter.output.persistence.collections;

import com.co.technicaltest.transaction_service.infrastructure.adapter.output.persistence.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "transacciones")
public class TransactionCollection {

    @Id
    private UUID id;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Field(name = "fecha_creacion")
    private LocalDateTime createAt;

    @NotNull
    @Field(name = "valor_transaccion")
    private BigDecimal transactionValue;

    @NotNull
    @Field(name = "balance_inicial_cuenta_origen")
    private BigDecimal initialBalanceAccountOrigin;

    @NotNull
    @Field(name = "balance_final_cuenta_origen")
    private BigDecimal finalBalanceAccountOrigin;

    @NotNull
    @Field(name = "balance_inicial_cuenta_destino")
    private BigDecimal initialBalanceAccountDestiny;

    @NotNull
    @Field(name = "balance_final_cuenta_destino")
    private BigDecimal finalBalanceAccountDestiny;

    @NotNull
    @Field(name = "cliente_id")
    private UUID customerId;

    @NotEmpty
    @Field(name = "nombre_cliente")
    private String customerName;

    @NotEmpty
    @Field(name = "numero_cuenta_origen")
    private String originAccountNumber;

    @NotEmpty
    @Field(name = "numero_cuenta_destino")
    private String destinyAccountNumber;

    @NotNull
    @Field(name = "estado")
    private TransactionStatus transactionStatus;

    @NotEmpty
    @Field(name = "estado")
    private String descriptionMovement;



}
