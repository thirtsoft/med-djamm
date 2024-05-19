package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.ExamenBiologique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamenBiologiqueRepository extends JpaRepository<ExamenBiologique, Long> {

    @Query("SELECT DISTINCT p from ExamenBiologique p where p.id=:id and p.actif=1")
    ExamenBiologique findExamenBiologiqueById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from ExamenBiologique p where p.actif=1")
    List<ExamenBiologique> findAllExamenBiologiques();

}
