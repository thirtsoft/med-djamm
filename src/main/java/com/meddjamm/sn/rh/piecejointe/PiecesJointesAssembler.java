package com.meddjamm.sn.rh.piecejointe;

import com.meddjamm.sn.rh.entity.TypeDocument;
import com.meddjamm.sn.rh.services.TypeDocumentService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PiecesJointesAssembler {

    private final TypeDocumentService typeDocumentService;

    public PiecesJointesAssembler(TypeDocumentService typeDocumentService) {
        this.typeDocumentService = typeDocumentService;
    }

    public List<PiecesJointesDs> createListPieceJointeDs(List<PiecesJointes> piecesJointes) {
        if (piecesJointes == null) {
            return Collections.emptyList();
        }
        return piecesJointes.stream().map(this::createPieceJointeDs).collect(Collectors.toList());
    }

    public PiecesJointesDs createPieceJointeDs(PiecesJointes piecesJointes) {
        if (piecesJointes == null) {
            return null;
        }
        PiecesJointesDs dto = new PiecesJointesDs();
        dto.setId(piecesJointes.getId());
        dto.setNomFichier(piecesJointes.getNomFichier());
        dto.setNomTechnique(piecesJointes.getNomTechnique());
        dto.setDossier(piecesJointes.getDossier());
        dto.setObjectId(piecesJointes.getObjectId());
        dto.setTypeDocumentId(piecesJointes.getTypeDocumentId());
        dto.setDateCreation(piecesJointes.getDateCreation());
        dto.setIdDocument(piecesJointes.getIdDocument());
        dto.setActif(piecesJointes.getActif());
        if (piecesJointes.getTypeDocumentId() != null) {
            TypeDocument typeDocument = typeDocumentService.findTypeDocumentById(piecesJointes.getTypeDocumentId());
            dto.setTypeDocumentLibelle(typeDocument.getLibelle());
        }
        return dto;
    }
}
