package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.Synthese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SyntheseRepository extends JpaRepository<Synthese, Long> {


    @Query("SELECT DISTINCT p from Synthese p where p.id=:id and p.actif=1")
    Synthese findSyntheseById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from Synthese p where p.actif=1")
    List<Synthese> findAllSyntheses();

    @Query("SELECT DISTINCT p from Synthese p where p.circuitPatient.code=:code and p.actif=1 ORDER BY p.id DESC")
    List<Synthese> findSyntheseByPatientId(@Param("code") String code);
}
