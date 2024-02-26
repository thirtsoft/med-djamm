package com.meddjamm.sn.rh.services.Impl;

import com.meddjamm.sn.rh.entity.AgentMedical;
import com.meddjamm.sn.rh.repository.AgentMedicalRepository;
import com.meddjamm.sn.rh.services.AgentMedicalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AgentMedicalServiceImpl implements AgentMedicalService {

    private final AgentMedicalRepository agentMedicalRepository;

    public AgentMedicalServiceImpl(AgentMedicalRepository agentMedicalRepository) {
        this.agentMedicalRepository = agentMedicalRepository;
    }

    @Override
    public void saveAgentMedical(AgentMedical agentMedical) {
        agentMedical.setActif(true);
        agentMedicalRepository.save(agentMedical);
    }

    @Override
    public void updateAgentMedical(Long id, AgentMedical agentMedical) throws Exception {
        if (!agentMedicalRepository.existsById(id)) {
            log.info("Agent that id is " + id + "not found");
        }
        agentMedical.setId(id);
        agentMedicalRepository.save(agentMedical);
    }

    @Override
    public AgentMedical findById(Long id) {
        return agentMedicalRepository.findAgentMedicalById(id);
    }

    @Override
    public List<AgentMedical> findAll() {
        return agentMedicalRepository.findAllAgentMedicals();
    }

    @Override
    public void deleteAgentMedical(Long id) {
        AgentMedical agentMedical = findById(id);
        agentMedical.setActif(false);
        agentMedicalRepository.save(agentMedical);
    }
}
