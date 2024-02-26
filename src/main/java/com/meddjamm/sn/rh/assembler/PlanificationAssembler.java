package com.meddjamm.sn.rh.assembler;

import com.meddjamm.sn.rh.entity.AgentMedical;
import com.meddjamm.sn.rh.entity.Planification;
import com.meddjamm.sn.rh.remote.model.PlanificationDetailDs;
import com.meddjamm.sn.rh.remote.model.PlanificationDs;
import com.meddjamm.sn.rh.services.AgentMedicalService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PlanificationAssembler {

    private final AgentMedicalService agentMedicalService;

    public PlanificationAssembler(AgentMedicalService agentMedicalService) {
        this.agentMedicalService = agentMedicalService;
    }

    public List<PlanificationDs> assembleEntitiesFrom(List<Planification> planifications) {
        return planifications.stream().map(this::assembleEntityToDs).toList();
    }

    public PlanificationDs assembleEntityToDs(Planification planification) {
        PlanificationDs planificationDs = new PlanificationDs();
        if (planification.getId() != null)
            planificationDs.setId(planification.getId());
        planificationDs.setAgentMedical(planification.getAgentMedical());
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
        planification.setAgentMedical(planificationDs.getAgentMedical());
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
        if (planification.getAgentMedical() != null) {
            AgentMedical agentMedical = agentMedicalService.findById(planification.getAgentMedical());
            String nomAgent = agentMedical.getUtilisateur().getPrenom() + ' ' + agentMedical.getUtilisateur().getNom();
            planificationDetailDs.setAgentMedical(planification.getAgentMedical());
            planificationDetailDs.setNomCompletAgent(nomAgent);
        }
        return planificationDetailDs;
    }

}
