package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassificationRepository extends JpaRepository<Classification, Long> {

    @Query("SELECT DISTINCT p from Classification p where p.id=:id and p.actif=1")
    Classification findClassificationById(@Param("id") Long id);
}
