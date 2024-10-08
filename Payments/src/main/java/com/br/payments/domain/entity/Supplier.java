package com.br.payments.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class Supplier implements Serializable {
    private static final String SEQNAME = "supplier_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQNAME)
    @SequenceGenerator(name = SEQNAME, sequenceName = SEQNAME, allocationSize = 1)
    private Long id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String legalName;

    @Pattern(regexp = "[0-9]*")
    @Size(min = 14, max = 14)
    @Column(length = 60, nullable = true)
    private String cnpj;

    @Size(min = 14, max = 14)
    @Column(length = 60, nullable = true)
    private String cpf;

    @Column(length = 255, nullable = false)
    private String ibgeCode;
}
