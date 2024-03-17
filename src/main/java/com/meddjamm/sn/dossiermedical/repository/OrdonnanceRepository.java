package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.Ordonnance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdonnanceRepository extends JpaRepository<Ordonnance, Long> {

    @Query("SELECT DISTINCT p from Ordonnance p where p.id=:id and p.actif=1")
    Ordonnance findOrdonnanceById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from Ordonnance p where p.actif=1")
    List<Ordonnance> findAllOrdonnances();

    @Query("SELECT DISTINCT p from Ordonnance p where p.circuitPatient.code=:code and p.actif=1 order by p.id desc")
    List<Ordonnance> findOrdonnanceByPatientId(@Param("code") String code);
}
