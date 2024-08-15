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
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_seq")
    @SequenceGenerator(name = "doc_seq", sequenceName = "doc_seq", allocationSize = 1)
    private Long id;

    @Column(length = 255, nullable = false)
    private String description;

    @Column(length = 255, nullable = false)
    private String fileName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_TYPEDOCUMENT")
    private TypeDocument typeDocument;


}
