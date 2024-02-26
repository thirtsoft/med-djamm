package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.ObservationClinique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObservationCliniqueRepository extends JpaRepository<ObservationClinique, Long> {

    @Query("SELECT DISTINCT p from ObservationClinique p where p.id=:id and p.actif=1")
    ObservationClinique findObservationCliniqueById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from ObservationClinique p where p.actif=1 order by p.id desc")
    List<ObservationClinique> findAllObservationCliniques();

    @Query("SELECT DISTINCT p from ObservationClinique p where p.circuitPatient.code=:code and p.actif=1")
    List<ObservationClinique> findObservationCliniqueByPatientId(@Param("code") String code);

}
