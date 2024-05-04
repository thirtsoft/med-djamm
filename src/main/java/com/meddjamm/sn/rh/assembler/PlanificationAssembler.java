package com.meddjamm.sn.rh.assembler;

import com.meddjamm.sn.config.assembler.UtilisateurAssembler;
import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.remote.model.UtilisateurDs;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.rh.entity.Planification;
import com.meddjamm.sn.rh.remote.model.PlanificationDetailDs;
import com.meddjamm.sn.rh.remote.model.PlanificationDs;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PlanificationAssembler {

    private final UtilisateurService utilisateurService;

    private final UtilisateurAssembler utilisateurAssembler;

    public PlanificationAssembler(UtilisateurService utilisateurService,
                                  UtilisateurAssembler utilisateurAssembler) {
        this.utilisateurService = utilisateurService;
        this.utilisateurAssembler = utilisateurAssembler;
    }

    public List<PlanificationDs> assembleEntitiesFrom(List<Planification> planifications) {
        return planifications.stream().map(this::assembleEntityToDs).toList();
    }

    public PlanificationDs assembleEntityToDs(Planification planification) {
        PlanificationDs planificationDs = new PlanificationDs();
        if (planification.getId() != null)
            planificationDs.setId(planification.getId());
        planificationDs.setAgentId(planification.getAgentId());
        planificationDs.setDateService(planification.getDateService());
        planificationDs.setCreatedDate(new Date());
        planificationDs.setActif(planification.isActif());
        planificationDs.setLibelle(planification.getLibelle());
        planificationDs.setIsCreatedBy(planification.getIsCreatedBy());
        if (planification.getAgentId() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurById(planification.getAgentId());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            planificationDs.setNomCompletAgent(nomAgent);
        }
        return planificationDs;
    }

    public Planification assemblePlanificationFromDs(PlanificationDs planificationDs) {
        Planification planification = new Planification();
        if (planificationDs.getId() != null)
            planification.setId(planificationDs.getId());
        planification.setAgentId(planificationDs.getAgentId());
        planification.setIsCreatedBy(planificationDs.getIsCreatedBy());
        planification.setDateService(planificationDs.getDateService());
        planification.setCreatedDate(new Date());
        planification.setActif(planificationDs.isActif());
        planification.setLibelle(planificationDs.getLibelle());
        return planification;
    }

    public List<PlanificationDetailDs> assembleDetailPlanificationsFromEntities(List<Planification> planifications) {
        return planifications.stream().map(this::assemblePlanificationDetailDsFromEntity).toList();
    }


    public PlanificationDetailDs assemblePlanificationDetailDsFromEntity(Planification planification) {
        PlanificationDetailDs planificationDetailDs = new PlanificationDetailDs();
        if (planification.getId() != null)
            planificationDetailDs.setId(planification.getId());
        planificationDetailDs.setDateService(planification.getDateService());
        planificationDetailDs.setIsCreatedBy(planification.getIsCreatedBy());
        planificationDetailDs.setCreatedDate(new Date());
        planificationDetailDs.setActif(planification.isActif());
        planificationDetailDs.setLibelle(planification.getLibelle());
        if (planification.getAgentId() != null) {
            Utilisateur agentMedical = utilisateurService.findUtilisateurById(planification.getAgentId());
            String nomAgent = agentMedical.getPrenom() + ' ' + agentMedical.getNom();
            UtilisateurDs utilisateurDs = utilisateurAssembler.assembleUtilisateurDsFromEntity(agentMedical);
            planificationDetailDs.setUtilisateurDs(utilisateurDs);
            planificationDetailDs.setNomCompletAgent(nomAgent);
        }
        return planificationDetailDs;
    }

}