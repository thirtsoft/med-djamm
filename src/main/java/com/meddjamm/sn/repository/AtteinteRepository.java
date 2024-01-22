package com.meddjamm.sn.repository;

import com.meddjamm.sn.entity.Atteinte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtteinteRepository extends JpaRepository<Atteinte, Long> {

    Atteinte findCritereMaladieByLibelle(String libelle);

    /*
    @Query("SELECT DISTINCT p from AntecedentChirurgie p where p.id=:id and p.actif=1")
    AntecedentChirurgie findAntecedentChirurgieById(@Param("id") Long id);*/
}
