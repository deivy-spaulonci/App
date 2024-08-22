package com.br.payments.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends Payment implements Serializable {
    private static final String SEQNAME = "account_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQNAME)
    @SequenceGenerator(name = SEQNAME, sequenceName = SEQNAME, allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_ACCOUNT_TYPE")
    private AccountType accountType;

    @Column(length = 60, nullable = false)
    private String barCode;
    //emissao
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate issuance;
    //vencimento
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate dueDate;
    //parcela
    @Column(length = 10, nullable = false)
    private Integer installment;
    //total de parcelas
    @Column(length = 10, nullable = false)
    private Integer installmentTotal;
    //multa
    @Column(precision = 10, scale = 2, nullable = true)
    private BigDecimal fine;
    //titulo boleto
    @Column
    private String ticket;
    //comprovante
    @Column
    private String proof;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable(name = "ACCOUNT_INVOICE",
            joinColumns = @JoinColumn(name = "ID_ACCOUNT"),
            inverseJoinColumns = @JoinColumn(name = "ID_INVOICE"))
    private List<Invoice> invoices;
}
