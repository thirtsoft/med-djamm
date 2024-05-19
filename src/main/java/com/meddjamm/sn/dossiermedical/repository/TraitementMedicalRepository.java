package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.TraitementMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TraitementMedicalRepository extends JpaRepository<TraitementMedical, Long> {

    @Query("SELECT DISTINCT o from TraitementMedical o where o.actif=1")
    List<TraitementMedical> findAll();
}
