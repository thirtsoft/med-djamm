package com.meddjamm.sn.repository;

import com.meddjamm.sn.config.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    @Query("SELECT DISTINCT p from Medecin p where p.matricule=:id and p.actif=1")
    Medecin findMedecinMatricule(@Param("id") String matricule);
}
