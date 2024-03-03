package com.meddjamm.sn.config.service;

import com.meddjamm.sn.config.entity.Action;

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
