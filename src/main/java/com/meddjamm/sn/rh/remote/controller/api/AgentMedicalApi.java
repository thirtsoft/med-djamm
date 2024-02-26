package com.meddjamm.sn.rh.remote.controller.api;

import com.meddjamm.sn.rh.remote.model.AgentMedicalDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/agentmedical")
public interface AgentMedicalApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    void creerAgentMedical(@RequestBody AgentMedicalDs agentMedicalDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateAgentMedical(@PathVariable Long id, @RequestBody AgentMedicalDs agentMedicalDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AgentMedicalDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AgentMedicalDs>> findAllAgentMedical();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteAgentMedical(@PathVariable Long id);
}
