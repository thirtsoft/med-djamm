package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.OrdonnanceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdonnanceItemRepository extends JpaRepository<OrdonnanceItem, Long> {

    @Query("SELECT DISTINCT p from OrdonnanceItem p where p.id=:id")
    OrdonnanceItem findOrdonnanceItemById(@Param("id") Long id);
}
