package com.br.payments.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Expense extends Payment implements Serializable {
    private static final String SEQNAME = "expense_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQNAME)
    @SequenceGenerator(name = SEQNAME, sequenceName = SEQNAME, allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private TypeExpenseEnum typeExpenseEnum;

    @ManyToOne(optional = false)
    @JoinColumn
    private Supplier supplier;
}
