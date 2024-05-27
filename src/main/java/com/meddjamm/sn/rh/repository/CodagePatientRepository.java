package com.meddjamm.sn.rh.repository;

import com.meddjamm.sn.rh.entity.CodagePatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodagePatientRepository extends JpaRepository<CodagePatient, Long> {

    @Query("SELECT DISTINCT m from CodagePatient m where m.id=:id and m.actif=1")
    CodagePatient findCodagePatientById(@Param("id") Long id);

    @Query("SELECT DISTINCT m from CodagePatient m where m.actif=1 ORDER BY m.id DESC")
    List<CodagePatient> findAllActiveCodagePatient();

}
