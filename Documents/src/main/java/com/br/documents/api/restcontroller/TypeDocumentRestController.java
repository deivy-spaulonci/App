package com.br.documents.api.restcontroller;

import com.br.documents.api.dto.TypeDocumentDto;
import com.br.documents.api.mapper.TypeDocumentMapper;
import com.br.documents.business.service.TypeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/type_document")
public class TypeDocumentRestController {
    private static final TypeDocumentMapper typeDocumentMapper = TypeDocumentMapper.INSTANCE;

    @Autowired
    private TypeDocumentService typeDocumentService;

    @GetMapping()
    public List<TypeDocumentDto> get(){
        return typeDocumentMapper.toDtoList(typeDocumentService.findAll());
    }
}
