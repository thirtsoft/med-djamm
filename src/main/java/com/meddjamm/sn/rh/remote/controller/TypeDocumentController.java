package com.meddjamm.sn.rh.remote.controller;

import com.meddjamm.sn.rh.assembler.TypeDocumentAssembler;
import com.meddjamm.sn.rh.remote.controller.api.TypeDocumentApi;
import com.meddjamm.sn.rh.remote.model.TypeDocumentDs;
import com.meddjamm.sn.rh.services.TypeDocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TypeDocumentController implements TypeDocumentApi {

    private final TypeDocumentService typeDocumentService;

    private final TypeDocumentAssembler typeDocumentAssembler;

    public TypeDocumentController(TypeDocumentService typeDocumentService,
                                  TypeDocumentAssembler typeDocumentAssembler) {
        this.typeDocumentService = typeDocumentService;
        this.typeDocumentAssembler = typeDocumentAssembler;
    }

    @Override
    public ResponseEntity<TypeDocumentDs> creerTypeDocument(TypeDocumentDs typeDocumentDs) {
        return new ResponseEntity<>(typeDocumentAssembler.assembleEntityToDs(
                typeDocumentService.saveTypeDocument(
                        typeDocumentAssembler.assembleTypeDocumentFromDs(typeDocumentDs)
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TypeDocumentDs> updateTypeDocument(Long id, TypeDocumentDs typeDocumentDs) throws Exception {
        return new ResponseEntity<>(typeDocumentAssembler.assembleEntityToDs(
                typeDocumentService.updateTypeDocument(id,
                        typeDocumentAssembler.assembleTypeDocumentFromDs(typeDocumentDs)
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TypeDocumentDs> findById(Long id) {
        return new ResponseEntity<>(typeDocumentAssembler.assembleEntityToDs(typeDocumentService.findTypeDocumentById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TypeDocumentDs>> findAllTypeDocuments() {
        return new ResponseEntity<>(
                typeDocumentAssembler.assembleEntitiesFrom(typeDocumentService.findAllTypeDocuments()), HttpStatus.OK
        );
    }

    @Override
    public void deleteTypeDocument(Long id) {
        typeDocumentService.deleteTypeDocument(id);
    }
}
