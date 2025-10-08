package com.co.technicaltest.infrastructure.adapter.output.persistence.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

/**
 * Entity for persisting customer information in a database.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class CustomerEntity extends PersonEntity {

    @NotNull
    @Column(name = "cliente_id",
            unique = true, nullable = false)
    private UUID customerId;

    @NotEmpty
    @Column(name = "nombre_usuario",
            unique = true, nullable = false)
    private String username;

    @NotEmpty
    @Column(name = "contraseña")
    private String password;

    @NotNull
    @Column(name = "estado")
    private Boolean status;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountEntity> accounts;

    public void removeAccount(AccountEntity account) {
        this.accounts.remove(account);
    }

    public void addAccount(AccountEntity account) {
        this.accounts.add(account);
    }


}
