package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.Ordonnance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdonnanceRepository extends JpaRepository<Ordonnance, Long> {

    @Query("SELECT DISTINCT o from Ordonnance o where o.actif=1")
    List<Ordonnance> findAll();
}
