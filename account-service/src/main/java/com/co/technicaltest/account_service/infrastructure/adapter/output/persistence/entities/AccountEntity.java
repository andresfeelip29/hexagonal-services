package com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.entities;

import com.co.technicaltest.account_service.domain.enums.BankAccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

/**
 * Entity for persisting account information in a database.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuentas")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "numero_cuenta",
            unique = true, nullable = false)
    private String accountNumber;

    @NotNull
    @Column(name = "tipo_cuenta")
    @Enumerated(EnumType.STRING)
    private BankAccountType accountType;

    @NotNull
    @Column(name = "saldo")
    private BigDecimal balance;

    @NotNull
    @Column(name = "estado")
    private Boolean status;

    @NotNull
    @ManyToOne( targetEntity = CustomerEntity.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "cliente_id")
    private CustomerEntity customer;

}
