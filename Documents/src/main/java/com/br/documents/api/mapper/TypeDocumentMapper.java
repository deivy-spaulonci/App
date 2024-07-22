package com.br.documents.api.mapper;

import com.br.documents.api.dto.TypeDocumentDto;
import com.br.documents.domain.entity.TypeDocument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TypeDocumentMapper {
    TypeDocumentMapper INSTANCE = Mappers.getMapper(TypeDocumentMapper.class);
    TypeDocument toModel(TypeDocumentDto typeDocumentDto);
    TypeDocumentDto toDTO(TypeDocument typeDocument);
    List<TypeDocumentDto> toDtoList(List<TypeDocument> typeDocumentList);
}
