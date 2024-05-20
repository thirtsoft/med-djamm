package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.ConsultationMedical;
import com.meddjamm.sn.dossiermedical.remote.model.AllCircuitPatientDs;
import com.meddjamm.sn.dossiermedical.remote.model.ConsultationMedicalDs;
import com.meddjamm.sn.dossiermedical.services.ConsultationMedicalService;
import com.meddjamm.sn.rh.piecejointe.PiecesJointesDs;
import com.meddjamm.sn.rh.piecejointe.PiecesJointesService;
import com.meddjamm.sn.utils.UtilString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsultationMedicalAssembler {

    private final UtilisateurService utilisateurService;

    private final ConsultationAssembler consultationAssembler;

    private final ConsultationMedicalService consultationMedicalService;

    private final ExamenBiologiqueAssembler examenBiologiqueAssembler;

    private final PiecesJointesService piecesJointesService;

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
        consultationMedicalDs.setPiecesJointesDs(piecesJointesService.getListPieceJointePatient(consultationMedical.getId()));
        List<PiecesJointesDs> piecesJointesDsBiologique = piecesJointesService.getListPieceJointePatient(consultationMedical.getExamenBiologique().getId());
        if (piecesJointesDsBiologique != null) {
            consultationMedicalDs.getPiecesJointesDs().addAll(piecesJointesDsBiologique);
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

    public ConsultationMedical assembleUpdateConsultationMedicallisteFromDs(ConsultationMedicalDs consultationMedicalDs) {
        ConsultationMedical consultationMedical = consultationMedicalService.findById(consultationMedicalDs.getId());
        if (consultationMedical.getConsultation() != null) {
            consultationMedical.setConsultation(consultationAssembler.assembleUpdateConsultationFromDs(
                    consultationMedical.getConsultation(), consultationMedicalDs.getConsultationDs()));
        }
        if (consultationMedical.getExamenBiologique() != null) {
            consultationMedical.setExamenBiologique(examenBiologiqueAssembler.assembleUpdateExamenBiologiqueFromDs(
                    consultationMedical.getExamenBiologique(), consultationMedicalDs.getExamenBiologiqueDs()));
        }
        return consultationMedical;
    }

    public List<AllCircuitPatientDs> assembleAllCircuitPatientEntitiesFrom(List<ConsultationMedical> consultationMedicalList) {
        return consultationMedicalList.stream().map(this::assembleAllCircuitPatientDsFromEntity).toList();
    }

    public AllCircuitPatientDs assembleAllCircuitPatientDsFromEntity(ConsultationMedical consultationMedical) {
        AllCircuitPatientDs allCircuitPatientDs = new AllCircuitPatientDs();
        if (consultationMedical.getId() != null)
            allCircuitPatientDs.setId(consultationMedical.getId());
        allCircuitPatientDs.setActif(consultationMedical.isActif());
        allCircuitPatientDs.setNumeroCircuit(UtilString.createNumeroCircuitPatient(
                consultationMedical.getCircuitPatient().getNumeroCircuit()));
        allCircuitPatientDs.setCreateDate(consultationMedical.getCreatedDate());
        allCircuitPatientDs.setType("Consultation médicale");
        if (consultationMedical.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(consultationMedical.getCreatedByUser());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            allCircuitPatientDs.setNomCompletAgent(nomAgent);
        }
        return allCircuitPatientDs;
    }

}
