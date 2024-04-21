package com.meddjamm.sn.rh.entity;

import com.meddjamm.sn.config.entity.AbstractAuditableEntity;
import com.meddjamm.sn.rh.remote.model.TypeDocumentDs;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "ged_type_document")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeDocument extends AbstractAuditableEntity implements Serializable {

    private String code;

    private String libelle;

    private int actif;

    public boolean isActif() {
        if (actif == 1)
            return true;
        else
            return false;
    }

    public void setActif(boolean actif) {
        if (actif == true)
            this.actif = 1;
        else
            this.actif = 0;
    }

    public TypeDocument(TypeDocumentDs typeDocumentDTO) {
        super();
        this.code = typeDocumentDTO.getCode();
        this.libelle = typeDocumentDTO.getLibelle();
    }

}
