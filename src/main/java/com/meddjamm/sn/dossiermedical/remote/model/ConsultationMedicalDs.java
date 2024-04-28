package com.meddjamm.sn.dossiermedical.remote.model;

import com.meddjamm.sn.rh.piecejointe.PiecesJointesDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationMedicalDs {

    private Long id;

    private String resume;

    private Long circuitPatientId;

    private ConsultationDs consultationDs;

    private ExamenBiologiqueDs examenBiologiqueDs;

    private Date createdDate;

    private Long createdBy;

    private String nomCompletAgent;

    private List<PiecesJointesDs> piecesJointesDs;

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
