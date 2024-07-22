package com.br.documents.api.mapper;

import com.br.documents.api.dto.DocumentDto;
import com.br.documents.domain.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DocumentMapper {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);
    Document toModel(DocumentDto documentDTO);
    DocumentDto toDTO(Document document);
    List<DocumentDto> toDtoList(List<Document> documentList);
}
