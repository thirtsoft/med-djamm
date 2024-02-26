package com.meddjamm.sn.rh.repository;

import com.meddjamm.sn.rh.entity.AgentMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgentMedicalRepository extends JpaRepository<AgentMedical, Long> {

    @Query("Select DISTINCT act from  AgentMedical act where act.id=:id and act.actif=1")
    AgentMedical findAgentMedicalById(@Param("id") Long id);

    @Query("Select DISTINCT act from  AgentMedical act where act.actif=1 order by act.id")
    List<AgentMedical> findAllAgentMedicals();
}
