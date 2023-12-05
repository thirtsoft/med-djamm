package com.meddjamm.sn.repository;

import com.meddjamm.sn.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT DISTINCT p from Patient p where p.id=:id and p.actif=1")
    Patient findPatientById(@Param("id") Long id);
}
