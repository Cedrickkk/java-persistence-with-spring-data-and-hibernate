package org.spring.data.sharedprimarykey.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String street;

    @NotNull
    private String zipCode;

    @NotNull
    private String city;

}