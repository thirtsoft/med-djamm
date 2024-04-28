package com.meddjamm.sn.dossiermedical.remote.model;

import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.rh.piecejointe.PiecesJointesDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalisationDetailDs {

    private Long id;

    private String numeroHospitalisation;

    private PatientDetailDs patientDetailDs;

    private UtilisateurDs utilisateurDs;

    private ObservationCliniqueDs observationCliniqueDs;


    //  private List<ExamenComplementaireDs> examenComplementaireDsList;

    private ExamenComplementaireDs examenComplementaireDs;

    //   private List<TraitementMedicalDs> traitementMedicalDsList;

    private TraitementMedicalDs traitementMedicalDs;

    //   private List<DiscussionDs> discussionDsList;

    private DiscussionDs discussionDs;

    //   private List<SyntheseDs> syntheseDsList;

    private List<PiecesJointesDs> piecesJointesDs;

    private SyntheseDs syntheseDs;

    private Long createdBy;

    private Date createdDate;

    private String resume;

    private String nomCompletMedecin;

    private int est_Transfer;

    private Date createDate;

    private int actif;

    public boolean isActif() {
        if (actif == 1) return true;
        else return false;
    }

    public void setActif(boolean actif) {
        if (actif == true) this.actif = 1;
        else this.actif = 0;
    }

}
