package com.br.documents.business.service;

import com.br.documents.domain.entity.Document;
import com.br.documents.domain.entity.TypeDocument;
import com.br.documents.domain.repository.DocumentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> findAll(){
        return documentRepository.findAll();
    }

    public Document findById(Long id) {
        return documentRepository.findById(id).orElse(null);
    }

    public Document save(Document document) {
        return documentRepository.save(document);
    }

    public List<Document> findByTypeDocuments_Id(Long idTypeDocument) {
        TypeDocument typeDocument = TypeDocument.builder().id(idTypeDocument).build();
        return documentRepository.findByTypeDocument(typeDocument);
    }


}
