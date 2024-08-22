package com.br.payments.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountType implements Serializable {
    private static final String SEQNAME = "account_type_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQNAME)
    @SequenceGenerator(name = SEQNAME, sequenceName = SEQNAME, allocationSize = 1)
    private Long id;
    @Column(length = 255, nullable = false)
    private String name;
    @Column
    private Boolean creditCard;
    @Column
    private Boolean active;
}
