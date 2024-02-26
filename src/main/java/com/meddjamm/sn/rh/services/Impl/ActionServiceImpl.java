package com.meddjamm.sn.rh.services.Impl;

import com.meddjamm.sn.rh.entity.Action;
import com.meddjamm.sn.rh.repository.ActionRepository;
import com.meddjamm.sn.rh.services.ActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;

    public ActionServiceImpl(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Override
    public void saveAction(Action action) {
        if (findByCode(action.getCode()) != null) {
            log.info("This action exist");
        }
        actionRepository.save(action);
    }

    @Override
    public void updateAction(Long id, Action action) throws Exception {
        if (!actionRepository.existsById(id)) {
            log.info("This action that id is " + id + "not found");
        }
        action.setId(id);
        actionRepository.save(action);
    }

    @Override
    public Action findById(Long id) {
        return actionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Action> findAll() {
        return actionRepository.findAll();
    }

    @Override
    public Action findByCode(String code) {
        return actionRepository.findByCode(code);
    }

    @Override
    public Action findByLibelle(String libelle, Long typeProfil) {
        return actionRepository.findByLibelle(libelle, typeProfil);
    }

    @Override
    public Action findByLibelle(String libelle) {
        return actionRepository.findByLibelle(libelle);
    }

    @Override
    public List<Action> findByTypeProfil(Long typeProfil) {
        return actionRepository.findByTypeProfil(typeProfil);
    }

    @Override
    public void deleteAction(Long id) {
        actionRepository.deleteById(id);
    }
}