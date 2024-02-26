package com.meddjamm.sn.rh.assembler;

import com.meddjamm.sn.assembler.UtilisateurAssembler;
import com.meddjamm.sn.rh.entity.AgentMedical;
import com.meddjamm.sn.rh.remote.model.AgentMedicalDs;
import com.meddjamm.sn.services.UtilisateurService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AgentMedicalAssembler {

    private final UtilisateurService utilisateurService;
    private final UtilisateurAssembler utilisateurAssembler;

    public AgentMedicalAssembler(UtilisateurService utilisateurService, UtilisateurAssembler utilisateurAssembler) {
        this.utilisateurService = utilisateurService;
        this.utilisateurAssembler = utilisateurAssembler;
    }

    public List<AgentMedicalDs> assembleEntitiesFrom(List<AgentMedical> agentMedicals) {
        return agentMedicals.stream().map(this::assembleEntityToDs).toList();
    }

    public AgentMedicalDs assembleEntityToDs(AgentMedical agentMedical) {
        AgentMedicalDs agentMedicalDs = new AgentMedicalDs();
        if (agentMedical.getId() != null) agentMedicalDs.setId(agentMedical.getId());
        agentMedicalDs.setCodeAgent(agentMedical.getCodeAgent());
        agentMedicalDs.setCivilite(agentMedical.getCivilite());
        agentMedicalDs.setSexe(agentMedical.getSexe());
        agentMedicalDs.setTelephone(agentMedical.getTelephone());
        agentMedicalDs.setPortable(agentMedical.getPortable());
        agentMedicalDs.setFonction(agentMedical.getFonction());
        agentMedicalDs.setDateRecrutement(agentMedical.getDateRecrutement());
        agentMedicalDs.setUtilisateurDs(utilisateurAssembler.assembleUtilisateurDsFromEntity(agentMedical.getUtilisateur()));
        return agentMedicalDs;

    }

    public AgentMedical assembleAgentMedicalFromDs(AgentMedicalDs agentMedicalDs) {
        AgentMedical agentMedical = new AgentMedical();
        if (agentMedical.getId() != null) agentMedical.setId(agentMedicalDs.getId());
        agentMedical.setCodeAgent(agentMedicalDs.getCodeAgent());
        agentMedical.setCivilite(agentMedicalDs.getCivilite());
        agentMedical.setSexe(agentMedicalDs.getSexe());
        agentMedical.setTelephone(agentMedicalDs.getTelephone());
        agentMedical.setPortable(agentMedicalDs.getPortable());
        agentMedical.setFonction(agentMedicalDs.getFonction());
        agentMedical.setDateRecrutement(agentMedicalDs.getDateRecrutement());
        agentMedical.setUtilisateur(utilisateurAssembler.assembleUtilisateurFromDs(agentMedicalDs.getUtilisateurDs()));
        return agentMedical;
    }

}
