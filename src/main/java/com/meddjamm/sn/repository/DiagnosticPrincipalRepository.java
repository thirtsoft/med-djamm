package com.meddjamm.sn.repository;

import com.meddjamm.sn.model.DiagnosticPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticPrincipalRepository extends JpaRepository<DiagnosticPrincipal, Long> {

    @Query("SELECT DISTINCT p from DiagnosticPrincipal p where p.id=:id and p.actif=1")
    DiagnosticPrincipal findDiagnosticPrincipalById(@Param("id") Long id);
}
