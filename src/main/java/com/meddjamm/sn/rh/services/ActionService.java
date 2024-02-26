package com.meddjamm.sn.rh.services;

import com.meddjamm.sn.rh.entity.Action;

import java.util.List;

public interface ActionService {

    void saveAction(Action action);

    void updateAction(Long id, Action action) throws Exception;

    Action findById(Long id);

    List<Action> findAll();

    Action findByCode(String code);

    Action findByLibelle(String libelle, Long typeProfil);

    Action findByLibelle(String libelle);

    List<Action> findByTypeProfil(Long typeProfil);

    void deleteAction(Long id);
}
