package com.br.documents.api.restcontroller;

import com.br.documents.api.dto.DocumentDto;
import com.br.documents.api.mapper.DocumentMapper;
import com.br.documents.business.service.DocumentService;
import com.br.documents.domain.entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentRestController {
    private static final DocumentMapper documentMapper = DocumentMapper.INSTANCE;

    @Autowired
    private DocumentService documentService;

    @GetMapping("/type_document/{id}")
    public List<DocumentDto> get(@PathVariable Long id){
        return documentMapper.toDtoList(documentService.findByTypeDocuments_Id(id));
    }

}
