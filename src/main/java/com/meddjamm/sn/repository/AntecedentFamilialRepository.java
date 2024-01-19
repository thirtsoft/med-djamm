package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.AntecedentFamilial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AntecedentFamilialRepository extends JpaRepository<AntecedentFamilial, Long> {

    @Query("SELECT DISTINCT p from AntecedentFamilial p where p.id=:id and p.actif=1")
    AntecedentFamilial findAntecedentFamilialById(@Param("id") Long id);
}
