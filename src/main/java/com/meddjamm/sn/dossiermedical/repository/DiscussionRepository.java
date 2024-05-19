package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    @Query("SELECT DISTINCT p from Discussion p where p.id=:id")
    Discussion findDiscussionById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from Discussion p")
    List<Discussion> findAllDiscussions();
}
