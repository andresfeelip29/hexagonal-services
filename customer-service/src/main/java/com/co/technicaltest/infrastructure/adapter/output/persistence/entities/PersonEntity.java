package com.co.technicaltest.infrastructure.adapter.output.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Entity for persisting person information in a database.
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
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name = "nombre")
    private String name;
    @NotNull
    @Column(name = "edad")
    private Integer age;
    @NotEmpty
    @Column(name = "genero")
    private String gender;
    @NotEmpty
    @Column(name = "indetificacion")
    private String identification;
    @NotEmpty
    @Column(name = "direccion")
    private String address;
    @NotEmpty
    @Column(name = "telefono")
    private String phone;
}
