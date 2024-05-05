package com.meddjamm.sn.rh.piecejointe;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pieces_jointes")
@Data
@AllArgsConstructor
public class PiecesJointes extends AbstractAuditableEntity implements Serializable {

    private String nomFichier;

    private String nomTechnique;

    private Long typeDocumentId;

    private Long objectId;

    private String dossier;

    private Date dateCreation;

    private int actif;

    private Long idDocument;

    public PiecesJointes() {
        super();
    }

    public PiecesJointes(PiecesJointesDs dto) {
        super();
        typeDocumentId = dto.getTypeDocumentId();
        objectId = dto.getObjectId();
        dossier = dto.getDossier();
        dateCreation = new Date();
        idDocument = dto.getIdDocument();
        actif = 1;
        nomFichier = dto.getNomFichier();
    }
}