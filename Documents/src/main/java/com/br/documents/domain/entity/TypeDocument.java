package com.br.documents.domain.entity;

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
public class TypeDocument  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_doc_seq")
    @SequenceGenerator(name = "type_doc_seq", sequenceName = "type_doc_seq", allocationSize = 1)
    private Long id;

    @Column(length = 255, nullable = false)
    private String nome;
}
