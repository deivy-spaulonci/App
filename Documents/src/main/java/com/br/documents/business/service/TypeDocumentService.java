package com.br.documents.business.service;

import com.br.documents.domain.entity.TypeDocument;
import com.br.documents.domain.repository.TypeDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeDocumentService {
    @Autowired
    private TypeDocumentRepository typeDocumentRepository;

    public List<TypeDocument> findAll() {
        return typeDocumentRepository.findAll();
    }

    public TypeDocument findById(Long id) {
        return typeDocumentRepository.findById(id).orElse(null);
    }

    public TypeDocument save(TypeDocument typeDocument) {
        return typeDocumentRepository.save(typeDocument);
    }
}
