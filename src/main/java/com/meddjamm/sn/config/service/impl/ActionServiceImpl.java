package com.meddjamm.sn.config.service.impl;

import com.meddjamm.sn.config.entity.Action;
import com.meddjamm.sn.config.repository.ActionRepository;
import com.meddjamm.sn.config.service.ActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;

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
        actionRepository.findById(id)
                .ifPresent(ac -> {
                    ac.setCode(action.getCode());
                    ac.setLibelle(action.getLibelle());
                });
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
        return actionRepository.findByLibelle(libelle);
    }

    @Override
    public Action findByLibelle(String libelle) {
        return actionRepository.findByLibelle(libelle);
    }

    @Override
    public List<Action> findByTypeProfil(Long typeProfil) {
        return emptyList();
    }

    @Override
    public void deleteAction(Long id) {
        actionRepository.deleteById(id);
    }
}