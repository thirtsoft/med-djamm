package com.meddjamm.sn.rh.remote.controller.api;

import com.meddjamm.sn.rh.remote.model.PlanificationDetailDs;
import com.meddjamm.sn.rh.remote.model.PlanificationDs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/planification")
public interface PlanificationApi {

    @PostMapping(value = "/save")
    void creerPlanification(@RequestBody PlanificationDs planificationDs);

    @PutMapping(value = "/edit/{id}")
    void updatePlanification(@PathVariable Long id, @RequestBody PlanificationDs planificationDs) throws Exception;

    @GetMapping(value = "/{id}")
    ResponseEntity<PlanificationDetailDs> findPlanificationById(@PathVariable Long id);

    @GetMapping(value = "/list")
    ResponseEntity<List<PlanificationDetailDs>> findAllPlanifications();

    @GetMapping(value = "/by-agent/{agentId}")
    ResponseEntity<List<PlanificationDetailDs>> findPlanificationsByAgent(@PathVariable Long agentId);

    @DeleteMapping(value = "/delete/{id}")
    void deletePlanification(@PathVariable Long id);
}
