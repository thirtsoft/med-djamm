package com.meddjamm.sn.rh.services.Impl;

import com.meddjamm.sn.rh.entity.Planification;
import com.meddjamm.sn.rh.repository.PlanificationRepository;
import com.meddjamm.sn.rh.services.PlanificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PlanificationServiceImpl implements PlanificationService {

    private final PlanificationRepository planificationRepository;

    public PlanificationServiceImpl(PlanificationRepository planificationRepository) {
        this.planificationRepository = planificationRepository;
    }

    @Override
    public void savePlanification(Planification planification) {
        planification.setActif(true);
        planification.setCreatedDate(new Date());
        planificationRepository.save(planification);
    }

    @Override
    public void updatePlanification(Long id, Planification planification) throws Exception {
        if (!planificationRepository.existsById(id)) {
            log.info("Planification that id is " + id + "not found");
        }
        planification.setId(id);
        planificationRepository.save(planification);
    }

    @Override
    public Planification findById(Long id) {
        return planificationRepository.findPlanificationById(id);
    }

    @Override
    public List<Planification> findAllPlanifications() {
        return planificationRepository.findAllPlanifications();
    }

    @Override
    public List<Planification> findAllPlanificationsByAgent(Long agent) {
        return planificationRepository.findPlanificationByAgent(agent);
    }

    @Override
    public void deletePlanification(Long id) {
        Planification planification = findById(id);
        planification.setActif(false);
        planificationRepository.save(planification);
    }
}
