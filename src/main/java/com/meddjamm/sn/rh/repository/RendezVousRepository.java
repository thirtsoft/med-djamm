package com.meddjamm.sn.rh.repository;

import com.meddjamm.sn.rh.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    @Query("SELECT DISTINCT p from RendezVous p where p.id=:id and p.actif=1")
    RendezVous findRendezVousById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from RendezVous p where p.actif=1 order by p.id desc")
    List<RendezVous> findAllRendezVous();

    @Query("SELECT DISTINCT p from RendezVous p where p.actif=1 and p.medecinId=:matricule")
    List<RendezVous> findRendezVousByDoctorMatricule(@Param("matricule") Long matricule);

}