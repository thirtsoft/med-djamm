package com.meddjamm.sn.rh.services;

import com.meddjamm.sn.rh.entity.Planification;

import java.util.List;

public interface PlanificationService {

    void savePlanification(Planification planification);

    void updatePlanification(Long id, Planification planification) throws Exception;

    Planification findById(Long id);

    List<Planification> findAllPlanifications();

    List<Planification> findAllPlanificationsByAgent(String agent);

    void deletePlanification(Long id);

}
