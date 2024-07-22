package com.br.documents.domain.repository;

import com.br.documents.domain.entity.Document;
import com.br.documents.domain.entity.TypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByTypeDocument(TypeDocument typeDocument);
}
