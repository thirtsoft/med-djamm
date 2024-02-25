package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.DiagnosticAssocie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosticAssocieRepository extends JpaRepository<DiagnosticAssocie, Long> {

    @Query("SELECT DISTINCT p from DiagnosticAssocie p where p.id=:id and p.actif=1")
    DiagnosticAssocie findDiagnosticAssocieById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from DiagnosticAssocie p where p.actif=1")
    List<DiagnosticAssocie> findAllDiagnosticAssocies();

}
