package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.Hospitalisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalisationRepository extends JpaRepository<Hospitalisation, Long> {

    @Query("SELECT DISTINCT p from Hospitalisation p where p.id=:id and p.actif=1")
    Hospitalisation findHospitalisationById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from Hospitalisation p where p.actif=1")
    List<Hospitalisation> findAllHospitalisations();

    @Query("SELECT DISTINCT p from Hospitalisation p where p.code=:code and p.actif=1 ORDER BY p.numeroHospitalisation DESC")
    List<Hospitalisation> findAllByPatient(@Param("code") String code);

    @Query("SELECT DISTINCT max(act.numeroHospitalisation) FROM Hospitalisation act")
    int maxNumeroHospitalisation();
}