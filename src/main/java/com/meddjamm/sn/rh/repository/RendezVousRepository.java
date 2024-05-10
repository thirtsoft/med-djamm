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

    // @Query("SELECT p from RendezVous p where p.actif=1 and p.medecinId=:matricule Group by (p.dateRendezVous) ORDER BY MIN(p.dateRendezVous) DESC")
    @Query("select p from RendezVous p where p.actif=1 and p.medecinId=:matricule Order by p.dateRendezVous DESC")
    List<RendezVous> findRendezVousByDoctorMatricule(@Param("matricule") Long matricule);

    @Query("SELECT DISTINCT p from RendezVous p where p.patientId=:patient and p.actif=1 ORDER BY p.id DESC LIMIT 3")
    List<RendezVous> findTreeLatestRendezVousByPatient(@Param("patient") Long code);

    @Query("SELECT DISTINCT r FROM RendezVous r WHERE r.dateRendezVous=current_date and r.actif=1 ORDER BY r.id")
    List<RendezVous> findAllRendezVousDay();

}
