package com.meddjamm.sn.rh.remote.controller;

import com.meddjamm.sn.rh.assembler.PlanificationAssembler;
import com.meddjamm.sn.rh.entity.Planification;
import com.meddjamm.sn.rh.remote.controller.api.PlanificationApi;
import com.meddjamm.sn.rh.remote.model.PlanificationDetailDs;
import com.meddjamm.sn.rh.remote.model.PlanificationDs;
import com.meddjamm.sn.rh.services.PlanificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200/")
public class PlanificationController implements PlanificationApi {

    private final PlanificationService planificationService;
    private final PlanificationAssembler planificationAssembler;

    public PlanificationController(PlanificationService planificationService,
                                   PlanificationAssembler planificationAssembler) {
        this.planificationService = planificationService;
        this.planificationAssembler = planificationAssembler;
    }

    @Override
    public void creerPlanification(PlanificationDs planificationDs) {
        Planification planification = planificationAssembler.assemblePlanificationFromDs(planificationDs);
        planificationService.savePlanification(planification);
    }

    @Override
    public void updatePlanification(Long id, PlanificationDs planificationDs) throws Exception {
        Planification planification = planificationAssembler.assemblePlanificationFromDs(planificationDs);
        planificationService.updatePlanification(id, planification);
    }

    @Override
    public ResponseEntity<PlanificationDetailDs> findPlanificationById(Long id) {
        return new ResponseEntity<>(planificationAssembler.assemblePlanificationDetailDsFromEntity(
                planificationService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PlanificationDetailDs>> findAllPlanifications() {
        return new ResponseEntity<>(planificationAssembler.assembleDetailPlanificationsFromEntities(
                planificationService.findAllPlanifications()
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PlanificationDetailDs>> findPlanificationsByAgent(Long agentId) {
        return new ResponseEntity<>(planificationAssembler.assembleDetailPlanificationsFromEntities(
                planificationService.findAllPlanificationsByAgent(agentId)
        ), HttpStatus.OK);
    }

    @Override
    public void deletePlanification(Long id) {
        planificationService.deletePlanification(id);
    }
}
