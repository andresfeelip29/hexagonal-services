package com.co.technicaltest.infrastructure.adapter.output.persistence.entities;


import com.co.technicaltest.infrastructure.shared.enums.BankAccountType;
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
@Table(name = "cliente_cuentas")
public class AccountEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private CustomerEntity customer;

    @NotEmpty
    @Column(name = "numero_cuenta")
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
}
