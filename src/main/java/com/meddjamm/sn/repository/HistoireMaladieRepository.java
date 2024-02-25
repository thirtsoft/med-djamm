package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.HistoireMaladie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoireMaladieRepository extends JpaRepository<HistoireMaladie, Long> {

    @Query("SELECT DISTINCT p from HistoireMaladie p where p.id=:id and p.actif=1")
    HistoireMaladie findHistoireMaladieById(@Param("id") Long id);
}
