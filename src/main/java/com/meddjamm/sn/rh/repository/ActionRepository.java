package com.meddjamm.sn.rh.repository;

import com.meddjamm.sn.rh.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long> {

    @Query("Select DISTINCT act from  Action act order by act.id")
    List<Action> findAll();

    @Query("Select DISTINCT act from  Action act where act.code=:code")
    Action findByCode(@Param("code") String code);

    @Query("Select DISTINCT act from  Action act where act.libelle=:libelle and act.typeProfil=:typeProfil")
    Action findByLibelle(@Param("libelle") String libelle, @Param("typeProfil") Long typeProfil);

    @Query("Select DISTINCT act from  Action act where act.libelle=:libelle")
    Action findByLibelle(@Param("libelle") String libelle);

    @Query("Select DISTINCT act from  Action act where act.typeProfil=:typeProfil order by act.id")
    List<Action> findByTypeProfil(@Param("typeProfil") Long typeProfil);
}
