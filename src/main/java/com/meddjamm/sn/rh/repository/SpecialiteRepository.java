package com.meddjamm.sn.rh.repository;

import com.meddjamm.sn.rh.entity.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {

    @Query("SELECT DISTINCT p from Specialite p where p.id=:id and p.actif=1")
    Specialite findSpecialiteById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from Specialite p where p.designation=:designation and p.actif=1")
    Specialite findSpecialiteByDesignation(@Param("designation") String designation);

    @Query("SELECT DISTINCT sp from Specialite sp where sp.actif=1")
    List<Specialite> findAllSpecialites();
}
