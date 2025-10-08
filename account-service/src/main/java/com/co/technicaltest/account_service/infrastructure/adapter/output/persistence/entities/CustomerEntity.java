package com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class CustomerEntity {

    @Id
    @Column(name = "id_cliente")
    private UUID customerId;

    @NotEmpty
    @Column(name = "nombre_usuario")
    private String username;

    @NotNull
    @Column(name = "estado")
    private Boolean status;

    @NotEmpty
    @Column(name = "nombre_cliente")
    private String name;


}
