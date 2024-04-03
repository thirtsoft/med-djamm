package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT DISTINCT p from Patient p where p.id=:id and p.actif=1")
    Patient findPatientById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from Patient p where p.code=:code and p.actif=1")
    Patient findPatientByCode(@Param("code") String code);

    @Query("SELECT COUNT(p) from Patient p where p.actif=1")
    long countActivePatient();
}
