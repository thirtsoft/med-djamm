package com.meddjamm.sn.rh.repository;

import com.meddjamm.sn.rh.entity.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {

    @Query("SELECT DISTINCT m from Medicament m where m.id=:id and m.actif=1")
    Medicament findMedicamentById(@Param("id") Long id);

    @Query("SELECT DISTINCT m from Medicament m where m.code=:code and m.actif=1")
    Medicament findMedicamentByCode(@Param("code") String code);

    @Query("SELECT DISTINCT m from Medicament m where m.actif=1")
    List<Medicament> findAll();

}