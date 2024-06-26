package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.ConsultationMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultationMedicalRepository extends JpaRepository<ConsultationMedical, Long> {

    @Query("SELECT DISTINCT p from ConsultationMedical p where p.id=:id and p.actif=1")
    ConsultationMedical findConsultationMedicalById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from ConsultationMedical p where p.actif=1")
    List<ConsultationMedical> findAllConsultationMedicals();

    @Query("SELECT DISTINCT p from ConsultationMedical p where p.circuitPatient.code=:code and p.actif=1 ORDER BY p.id DESC")
    List<ConsultationMedical> findConsultationMedicalByPatientId(@Param("code") String code);

    @Query("SELECT DISTINCT p from ConsultationMedical p where p.circuitPatient.id=:code and p.actif=1 ORDER BY p.id DESC LIMIT 3")
    List<ConsultationMedical> findConsultationMedicalByCircuitId(@Param("code") Long code);

    @Query("SELECT COUNT(c) from ConsultationMedical c where c.circuitPatient.code=:code and c.actif=1")
    long countActiveConsultationMedicalByPatient(@Param("code") String code);

}
