package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.ExamenPhysique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamenPhysiqueRepository extends JpaRepository<ExamenPhysique, Long> {

    @Query("SELECT DISTINCT p from ExamenPhysique p where p.id=:id and p.actif=1")
    ExamenPhysique findExamenPhysiqueById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from ExamenPhysique p where p.actif=1 order by p.id desc")
    List<ExamenPhysique> findAllExamenPhysiques();

    @Query("SELECT DISTINCT p from ExamenPhysique p where p.observationClinique.id=:id")
    List<ExamenPhysique> findAllExamenPhysiquesByObservationCliniqueId(@Param("id") Long id);
}
