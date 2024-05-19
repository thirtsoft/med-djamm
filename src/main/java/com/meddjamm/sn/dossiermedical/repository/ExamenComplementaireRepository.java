package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.ExamenComplementaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamenComplementaireRepository extends JpaRepository<ExamenComplementaire, Long> {

    @Query("SELECT DISTINCT p from ExamenComplementaire p where p.id=:id and p.actif=1")
    ExamenComplementaire findExamenComplementaireById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from ExamenComplementaire p where p.actif=1")
    List<ExamenComplementaire> findAllExamenComplementaires();
}
