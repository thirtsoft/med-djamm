package com.meddjamm.sn.dossiermedical.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamenBiologiqueDs {

    private Long id;

    private Long circuitPatientId;

    private Long createdBy;

    private String biologie;

    private String biologieFileName;

    private String immunologie;

    private String immunologieFileName;

    private String imagerie;

    private String imagerieFileName;

    private String anatomopathologie;

    private String anatomopathologieFileName;

    private Date createdDate;

    private String nomCompletAgent;

    private int actif;

    public boolean isActif() {
        return actif == 1;
    }

    public void setActif(boolean actif) {
        if (actif)
            this.actif = 1;
        else
            this.actif = 0;
    }
}
