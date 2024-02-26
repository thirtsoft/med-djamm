package com.meddjamm.sn.rh.remote.controller;

import com.meddjamm.sn.rh.assembler.AgentMedicalAssembler;
import com.meddjamm.sn.rh.entity.AgentMedical;
import com.meddjamm.sn.rh.remote.controller.api.AgentMedicalApi;
import com.meddjamm.sn.rh.remote.model.AgentMedicalDs;
import com.meddjamm.sn.rh.services.AgentMedicalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200/")
public class AgentMedicalController implements AgentMedicalApi {

    private final AgentMedicalService agentMedicalService;

    private final AgentMedicalAssembler agentMedicalAssembler;

    public AgentMedicalController(AgentMedicalService agentMedicalService,
                                  AgentMedicalAssembler agentMedicalAssembler) {
        this.agentMedicalService = agentMedicalService;
        this.agentMedicalAssembler = agentMedicalAssembler;
    }

    @Override
    public void creerAgentMedical(AgentMedicalDs agentMedicalDs) {
        AgentMedical agentMedical = agentMedicalAssembler.assembleAgentMedicalFromDs(agentMedicalDs);
        agentMedicalService.saveAgentMedical(agentMedical);
    }

    @Override
    public void updateAgentMedical(Long id, AgentMedicalDs agentMedicalDs) throws Exception {
        AgentMedical agentMedical = agentMedicalAssembler.assembleAgentMedicalFromDs(agentMedicalDs);
        agentMedicalService.updateAgentMedical(id, agentMedical);
    }

    @Override
    public ResponseEntity<AgentMedicalDs> findById(Long id) {
        return new ResponseEntity<>(agentMedicalAssembler.assembleEntityToDs(
                agentMedicalService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AgentMedicalDs>> findAllAgentMedical() {
        return new ResponseEntity<>(agentMedicalAssembler.assembleEntitiesFrom(
                agentMedicalService.findAll()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteAgentMedical(Long id) {
        agentMedicalService.deleteAgentMedical(id);
    }
}
