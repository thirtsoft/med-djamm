package com.meddjamm.sn.config.service.impl;

import com.meddjamm.sn.config.entity.Action;
import com.meddjamm.sn.config.entity.Profil;
import com.meddjamm.sn.config.repository.ActionRepository;
import com.meddjamm.sn.config.repository.ProfilRepository;
import com.meddjamm.sn.config.service.ActionService;
import com.meddjamm.sn.exception.ActionAlreadyExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;

    private final ProfilRepository profilRepository;

    @Override
    public void saveAction(Action action) {
        if (findByCode(action.getCode()) != null) {
            log.info("This action exist");
            throw new ActionAlreadyExistException(action.getCode() + " action already exist");
        }
        actionRepository.save(action);
    }

    @Override
    public void updateAction(Long id, Action action) {
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
//        this.removeAllProfilFromAction(id);
        actionRepository.deleteById(id);
    }

    @Override
    public List<Action> getListActionByProfil(String codeProfil) {
        Profil profil = profilRepository.findByCodeFromAction(codeProfil);
        List<Action> actionList = new ArrayList<>(profil.getAction());
        return actionList;
    }

//    @Override
//    public Action removeAllProfilFromAction(Long roleId) {
//        Optional<Action> role = actionRepository.findById(roleId);
//        role.ifPresent(Action::removeAllProfilesFromAction);
//        return actionRepository.save(role.get());
//    }
//
//    @Override
//    public Profil assignProfilToAction(Long profilId, Long actionId) {
//        Optional<Profil> profil = profilRepository.findById(profilId);
//        Optional<Action> action = actionRepository.findById(actionId);
//        if (profil.isPresent() && profil.get().getAction().contains(action.get())) {
//            throw new ProfilAlreadyExistsException(profil.get().getCode() +
//                    " is already assigned to the " + action.get().getCode() + " action");
//        }
//        action.ifPresent(theRole -> theRole.assignProfileToAction(profil.get()));
//        actionRepository.save(action.get());
//        return profil.get();
//    }
//
//    @Override
//    public Profil removeProfilFromAction(Long profilId, Long actionId) {
//        Optional<Profil> user = profilRepository.findById(profilId);
//        Optional<Action> role = actionRepository.findById(actionId);
//        if (role.isPresent() && role.get().getProfils().contains(user.get())) {
//            role.get().removeProfileToAction(user.get());
//            actionRepository.save(role.get());
//            return user.get();
//        }
//        throw new ProfilNotFoundException("Profil not found!");
//    }
}