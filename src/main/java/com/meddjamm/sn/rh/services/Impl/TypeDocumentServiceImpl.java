package com.meddjamm.sn.rh.services.Impl;

import com.meddjamm.sn.rh.entity.TypeDocument;
import com.meddjamm.sn.rh.repository.TypeDocumentRepository;
import com.meddjamm.sn.rh.services.TypeDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TypeDocumentServiceImpl implements TypeDocumentService {

    private final TypeDocumentRepository typeDocumentRepository;

    public TypeDocumentServiceImpl(TypeDocumentRepository typeDocumentRepository) {
        this.typeDocumentRepository = typeDocumentRepository;
    }

    @Override
    public TypeDocument saveTypeDocument(TypeDocument typeDocument) {
        if (typeDocumentRepository.findTypeDocumentByCode(typeDocument.getCode()) != null) {
            log.info("Ce type de document exist");
        }
        typeDocument.setActif(true);
        return typeDocumentRepository.saveAndFlush(typeDocument);
    }

    @Override
    public TypeDocument updateTypeDocument(Long id, TypeDocument typeDocument) throws Exception {
        if (!typeDocumentRepository.existsById(id)) {
            throw new Exception("This document type is not found");
        }
        TypeDocument typeDocumentResult = typeDocumentRepository.findTypeDocumentById(id);
        if (typeDocumentResult == null) {
            throw new Exception("This document type is not found");
        }
        typeDocumentResult.setCode(typeDocument.getCode());
        typeDocumentResult.setLibelle(typeDocument.getLibelle());
        return typeDocumentRepository.saveAndFlush(typeDocumentResult);
    }

    @Override
    public TypeDocument findTypeDocumentById(Long id) {
        return typeDocumentRepository.findTypeDocumentById(id);
    }

    @Override
    public List<TypeDocument> findAllTypeDocuments() {
        return typeDocumentRepository.findAll();
    }

    @Override
    public void deleteTypeDocument(Long id) {
        TypeDocument typeDocument = findTypeDocumentById(id);
        typeDocument.setActif(false);
        typeDocumentRepository.saveAndFlush(typeDocument);
    }
}
