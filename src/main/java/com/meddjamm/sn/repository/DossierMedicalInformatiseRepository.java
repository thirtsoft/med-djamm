package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.DossierMedicalInformatise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DossierMedicalInformatiseRepository extends JpaRepository<DossierMedicalInformatise, Long> {

    @Query("SELECT DISTINCT p from DossierMedicalInformatise p where p.id=:id and p.actif=1")
    DossierMedicalInformatise findDossierMedicalInformatiseById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from DossierMedicalInformatise p where p.numeroDossierMedical=:numero and p.actif=1")
    DossierMedicalInformatise findDossierMedicalInformatiseByNumero(@Param("numero") int numero);

    @Query("SELECT DISTINCT p from DossierMedicalInformatise p where p.actif=1 order by p.id desc")
    List<DossierMedicalInformatise> findAllDossierMedicalInformatises();

    @Query("SELECT DISTINCT p from DossierMedicalInformatise p where p.code=:code and p.actif=1 order by p.id desc")
    List<DossierMedicalInformatise> findDossierMedicalInformatiseByPatient(@Param("code") String code);

    @Query("SELECT DISTINCT max(act.numeroDossierMedical) FROM DossierMedicalInformatise act")
    int maxNumeroDossierMedical();
}
