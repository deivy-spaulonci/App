package com.br.documents.business.service;

import com.br.documents.domain.entity.Document;
import com.br.documents.domain.repository.DocumentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class DocumentService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> findAll(){
        return documentRepository.findAll();
    }


}
