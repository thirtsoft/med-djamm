package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.AvisSpecialiste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvisSpecialisteRepository extends JpaRepository<AvisSpecialiste, Long> {

    @Query("SELECT DISTINCT p from AvisSpecialiste p where p.id=:id and p.actif=1")
    AvisSpecialiste findAvisSpecialisteById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from AvisSpecialiste p where p.actif=1")
    List<AvisSpecialiste> findAllAvisSpecialistes();

    @Query("SELECT DISTINCT p from AvisSpecialiste p where p.circuitPatient.code=:code and p.actif=1 ORDER BY p.id DESC")
    List<AvisSpecialiste> findAvisSpecialisteByPatientId(@Param("code") String code);

    @Query("SELECT DISTINCT p from AvisSpecialiste p where p.circuitPatient.id=:code and p.actif=1 ORDER BY p.id DESC")
    List<AvisSpecialiste> findAvisSpecialisteByCircuitId(@Param("code") Long code);
}
