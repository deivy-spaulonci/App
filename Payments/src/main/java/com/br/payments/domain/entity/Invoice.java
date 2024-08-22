package com.br.payments.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Invoice {
    @Id
    @Column
    private Long id;

    @Column(length = 10, nullable = false)
    private Integer installment;

    @Column(length = 10, nullable = false)
    private Integer installmentTotal;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate paymentDate;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "ID_SUPPLIER", nullable = true)
    private Supplier supplier;
}
