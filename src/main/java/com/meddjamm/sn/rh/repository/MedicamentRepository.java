package com.meddjamm.sn.rh.repository;

import com.meddjamm.sn.rh.entity.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {

    @Query("SELECT DISTINCT m from Medicament m where m.id=:id and m.actif=1")
    Medicament findMedicamentById(@Param("id") Long id);

    @Query("SELECT DISTINCT m from Medicament m where m.code=:code and m.actif=1")
    Medicament findMedicamentByCode(@Param("code") String code);

    @Query("SELECT DISTINCT m from Medicament m where m.actif=1 ORDER BY m.id DESC")
    List<Medicament> findAll();

    @Query("SELECT m FROM Medicament m WHERE lower(m.code) = lower(:code) AND m.actif = 1")
    Optional<Medicament> findByCode(@Param("code") String code);


}
