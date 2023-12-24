package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.Maladie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MaladieRepository extends JpaRepository<Maladie, Long> {

    @Query("SELECT DISTINCT p from Maladie p where p.id=:id and p.actif=1")
    Maladie findMaladieById(@Param("id") Long id);
}
