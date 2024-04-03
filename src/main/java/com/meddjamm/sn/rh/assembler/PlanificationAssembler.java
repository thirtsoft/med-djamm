package com.meddjamm.sn.rh.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
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

    public PlanificationAssembler(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public List<PlanificationDs> assembleEntitiesFrom(List<Planification> planifications) {
        return planifications.stream().map(this::assembleEntityToDs).toList();
    }

    public PlanificationDs assembleEntityToDs(Planification planification) {
        PlanificationDs planificationDs = new PlanificationDs();
        if (planification.getId() != null)
            planificationDs.setId(planification.getId());
        planificationDs.setAgentMedical(planification.getMatricule());
        planificationDs.setDateService(planification.getDateService());
        planificationDs.setCreatedDate(new Date());
        planificationDs.setActif(planification.isActif());
        planificationDs.setLibelle(planification.getLibelle());
        return planificationDs;
    }

    public Planification assemblePlanificationFromDs(PlanificationDs planificationDs) {
        Planification planification = new Planification();
        if (planificationDs.getId() != null)
            planification.setId(planificationDs.getId());
        planification.setMatricule(planificationDs.getAgentMedical());
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
        planificationDetailDs.setCreatedDate(new Date());
        planificationDetailDs.setActif(planification.isActif());
        planificationDetailDs.setLibelle(planification.getLibelle());
        if (planification.getMatricule() != null) {
            Utilisateur agentMedical = utilisateurService.findUtilisateurByMatricule(planification.getMatricule());
            String nomAgent = agentMedical.getPrenom() + ' ' + agentMedical.getNom();
            planificationDetailDs.setAgentMedical(planification.getMatricule());
            planificationDetailDs.setNomCompletAgent(nomAgent);
            if (planification.getMatricule() != null) {
                planificationDetailDs.setAgentMedical(planification.getMatricule());
            }
        }
        return planificationDetailDs;
    }

}