package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.ConsultationMedical;
import com.meddjamm.sn.dossiermedical.remote.model.ConsultationMedicalDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultationMedicalAssembler {

    private final UtilisateurService utilisateurService;

    private final ConsultationAssembler consultationAssembler;

    private final ExamenBiologiqueAssembler examenBiologiqueAssembler;

    public ConsultationMedicalAssembler(UtilisateurService utilisateurService,
                                        ConsultationAssembler consultationAssembler,
                                        ExamenBiologiqueAssembler examenBiologiqueAssembler) {
        this.utilisateurService = utilisateurService;
        this.consultationAssembler = consultationAssembler;
        this.examenBiologiqueAssembler = examenBiologiqueAssembler;
    }


    public List<ConsultationMedicalDs> assembleEntitiesFrom(List<ConsultationMedical> consultationMedicalList) {
        return consultationMedicalList.stream().map(this::assembleEntityToDs).toList();
    }

    public ConsultationMedicalDs assembleEntityToDs(ConsultationMedical consultationMedical) {
        ConsultationMedicalDs consultationMedicalDs = new ConsultationMedicalDs();
        if (consultationMedical.getId() != null)
            consultationMedicalDs.setId(consultationMedical.getId());
        consultationMedicalDs.setActif(consultationMedical.isActif());
        consultationMedicalDs.setCreatedDate(consultationMedical.getCreatedDate());
        consultationMedicalDs.setConsultationDs(consultationAssembler.assembleEntityToDs(consultationMedical.getConsultation()));
        consultationMedicalDs.setExamenBiologiqueDs(examenBiologiqueAssembler.assembleEntityToDs(consultationMedical.getExamenBiologique()));
        consultationMedicalDs.setCreatedBy(consultationMedical.getCreatedBy());
        consultationMedicalDs.setCircuitPatientId(consultationMedical.getCircuitPatientId());
        if (consultationMedical.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUserById(consultationMedical.getCreatedBy());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            consultationMedicalDs.setNomCompletAgent(nomAgent);
        }
        return consultationMedicalDs;
    }

    public ConsultationMedical assembleConsultationMedicallisteFromDs(ConsultationMedicalDs consultationMedicalDs) {
        ConsultationMedical consultationMedical = new ConsultationMedical();
        if (consultationMedicalDs.getId() != null)
            consultationMedical.setId(consultationMedicalDs.getId());
        consultationMedical.setActif(consultationMedicalDs.isActif());
        consultationMedical.setCreatedDate(consultationMedicalDs.getCreatedDate());
        consultationMedical.setConsultation(consultationAssembler.assembleConsultationFromDs(consultationMedicalDs.getConsultationDs()));
        consultationMedical.setExamenBiologique(examenBiologiqueAssembler.assembleExamenBiologiqueFromDs(consultationMedicalDs.getExamenBiologiqueDs()));
        consultationMedical.setCreatedBy(consultationMedicalDs.getCreatedBy());
        consultationMedical.setCircuitPatientId(consultationMedicalDs.getCircuitPatientId());
        return consultationMedical;
    }

}
