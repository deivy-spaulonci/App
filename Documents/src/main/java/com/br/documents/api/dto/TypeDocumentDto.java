package com.br.documents.api.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeDocumentDto {
    private Long id;
    private String name;
}
