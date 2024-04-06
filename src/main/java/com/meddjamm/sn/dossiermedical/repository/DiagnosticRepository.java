package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {

    @Query("SELECT DISTINCT p from Diagnostic p where p.id=:id and p.actif=1")
    Diagnostic findDiagnosticById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from Diagnostic p where p.actif=1")
    List<Diagnostic> findAllDiagnostics();

}
