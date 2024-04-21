package com.meddjamm.sn.rh.services;

import com.meddjamm.sn.rh.entity.TypeDocument;

import java.util.List;

public interface TypeDocumentService {

    TypeDocument saveTypeDocument(TypeDocument typeDocument);

    TypeDocument updateTypeDocument(Long id, TypeDocument typeDocument) throws Exception;

    TypeDocument findTypeDocumentById(Long id);

    List<TypeDocument> findAllTypeDocuments();

    void deleteTypeDocument(Long id);
}
