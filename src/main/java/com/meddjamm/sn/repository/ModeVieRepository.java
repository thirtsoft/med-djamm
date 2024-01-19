package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.ModeVie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeVieRepository extends JpaRepository<ModeVie, Long> {

    @Query("SELECT DISTINCT p from ModeVie p where p.id=:id and p.actif=1")
    ModeVie findModeVieById(@Param("id") Long id);
}
