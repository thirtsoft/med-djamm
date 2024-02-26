package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.OrdonnanceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdonnanceItemRepository extends JpaRepository<OrdonnanceItem, Long> {
}
