package com.meddjamm.sn.rh.repository;

import com.meddjamm.sn.rh.entity.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfilRepository extends JpaRepository<Profil, Long> {

    @Query("Select DISTINCT act from  Profil act where act.actif=1 and act.id != 4 and act.id != 10 and act.id != 11 order by act.code")
    List<Profil> findAllActive();

    @Query("Select DISTINCT act from  Profil act where act.id=:id")
    Profil findProfilById(@Param("id") Long id);

    @Query("Select DISTINCT act from  Profil act where act.actif=1 AND act.code=:code")
    Profil findByCode(@Param("code") String code);

    @Query("Select DISTINCT act from  Profil act where act.actif=1 AND act.libelle=:libelle")
    Profil findByLibelle(@Param("libelle") String libelle);

    @Query("Select DISTINCT act from  Profil act where act.actif=1 AND act.code=:code")
    Optional<Profil> findByProfilCode(@Param("code") String code);

    @Query("Select DISTINCT act from  Profil act where act.code=:code")
    Profil findByCodeFromAction(@Param("code") String code);
}
