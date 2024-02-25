package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.DossierPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DossierPatientRepository extends JpaRepository<DossierPatient, Long> {

    @Query("SELECT DISTINCT p from DossierPatient p where p.id=:id and p.actif=1")
    DossierPatient findDossierPatientById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from DossierPatient p where p.actif=1 order by p.id desc")
    List<DossierPatient> findAllDossierPatients();

    @Query("SELECT DISTINCT p from DossierPatient p where p.indexPatient=:indexPatient and p.actif=1 order by p.id desc")
    List<DossierPatient> findDossierPatientsByPatient(@Param("indexPatient") String indexPatient);

    @Query("SELECT DISTINCT max(act.numeroDossier) FROM DossierPatient act")
    int maxNumeroDossier();

}
