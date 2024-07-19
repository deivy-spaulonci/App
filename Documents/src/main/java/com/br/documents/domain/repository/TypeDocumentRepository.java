package com.br.documents.domain.repository;

import com.br.documents.domain.entity.TypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {
}
