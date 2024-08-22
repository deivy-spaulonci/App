package com.br.payments.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Payment implements Serializable {
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column
    private TypePaymentMethodEnum typePaymentMethodEnum;

    @Column(nullable = true, columnDefinition = "DATE")
    private LocalDate paymentDate;

    @Column(insertable = false, nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime releaseDate;

    @Column(length = 255, nullable = true)
    private String obs;
}
