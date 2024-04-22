package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    @Query("SELECT DISTINCT p from Discussion p where p.id=:id and p.actif=1")
    Discussion findDiscussionById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from Discussion p where p.actif=1")
    List<Discussion> findAllDiscussions();

    @Query("SELECT DISTINCT p from Discussion p where p.circuitPatient.code=:code and p.actif=1 ORDER BY p.id DESC")
    List<Discussion> findDiscussionByPatientId(@Param("code") String code);
}
