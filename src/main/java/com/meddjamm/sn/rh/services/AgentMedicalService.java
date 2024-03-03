package com.meddjamm.sn.rh.services;

import com.meddjamm.sn.config.entity.AgentMedical;

import java.util.List;

public interface AgentMedicalService {

    void saveAgentMedical(AgentMedical agentMedical);

    void updateAgentMedical(Long id, AgentMedical agentMedical) throws Exception;

    AgentMedical findById(Long id);

    List<AgentMedical> findAll();

    void deleteAgentMedical(Long id);
}
