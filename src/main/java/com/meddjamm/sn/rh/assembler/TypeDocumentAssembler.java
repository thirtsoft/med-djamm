package com.meddjamm.sn.rh.assembler;

import com.meddjamm.sn.rh.entity.TypeDocument;
import com.meddjamm.sn.rh.remote.model.TypeDocumentDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypeDocumentAssembler {

    public List<TypeDocumentDs> assembleEntitiesFrom(List<TypeDocument> typeDocumentList) {
        return typeDocumentList.stream().map(this::assembleEntityToDs).toList();
    }

    public TypeDocumentDs assembleEntityToDs(TypeDocument typeDocument) {
        TypeDocumentDs typeDocumentDs = new TypeDocumentDs();
        typeDocumentDs.setId(typeDocument.getId());
        typeDocumentDs.setCode(typeDocument.getCode());
        typeDocumentDs.setLibelle(typeDocument.getLibelle());
        typeDocumentDs.setActif(typeDocument.isActif());
        return typeDocumentDs;
    }

    public TypeDocument assembleTypeDocumentFromDs(TypeDocumentDs typeDocumentDs) {
        TypeDocument typeDocument = new TypeDocument();
        typeDocument.setId(typeDocumentDs.getId());
        typeDocument.setCode(typeDocumentDs.getCode());
        typeDocument.setLibelle(typeDocumentDs.getLibelle());
        typeDocument.setActif(typeDocumentDs.isActif());
        return typeDocument;
    }
}
