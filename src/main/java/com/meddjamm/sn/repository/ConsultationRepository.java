package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    @Query("SELECT DISTINCT p from Consultation p where p.id=:id and p.actif=1")
    Consultation findConsultationById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from Consultation p where p.actif=1")
    List<Consultation> findAllConsultations();
}
