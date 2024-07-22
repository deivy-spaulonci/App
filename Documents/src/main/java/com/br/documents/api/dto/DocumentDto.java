package com.br.documents.api.dto;

import com.br.documents.domain.entity.TypeDocument;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto implements Serializable{

    private Long id;
    @NotNull
    private String description;
    private String file;
    @NotNull
    private TypeDocument typeDocument;
}
