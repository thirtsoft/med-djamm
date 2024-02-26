package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.Hospitalisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalisationRepository extends JpaRepository<Hospitalisation, Long> {

    @Query("SELECT DISTINCT p from Hospitalisation p where p.id=:id and p.actif=1")
    Hospitalisation findHospitalisationById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from Hospitalisation p where p.actif=1")
    List<Hospitalisation> findAllHospitalisations();
}
