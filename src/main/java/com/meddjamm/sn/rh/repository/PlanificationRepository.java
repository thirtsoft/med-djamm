package com.meddjamm.sn.rh.repository;

import com.meddjamm.sn.rh.entity.Planification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanificationRepository extends JpaRepository<Planification, Long> {

    @Query("SELECT DISTINCT p from Planification p where p.id=:id and p.actif=1")
    Planification findPlanificationById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from Planification p where p.actif=1 order by p.id desc")
    List<Planification> findAllPlanifications();

    @Query("SELECT DISTINCT p from Planification p where p.matricule=:agentId and p.actif=1 order by p.id desc")
    List<Planification> findPlanificationByAgent(@Param("agentId") String agentId);

}
