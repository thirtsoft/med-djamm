package com.meddjamm.sn.config.assembler;

import com.meddjamm.sn.config.entity.Action;
import com.meddjamm.sn.config.remote.model.ActionListDs;
import com.meddjamm.sn.config.service.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ActionAssembler {

    private final ActionService actionService;

    public List<ActionListDs> assembleEntitiesFrom(List<Action> actions) {
        return actions.stream().map(this::assembleEntityToDs).toList();
    }

    public ActionListDs assembleEntityToDs(Action action) {
        ActionListDs actionListDs = new ActionListDs();
        actionListDs.setId(action.getId());
        actionListDs.setCode(action.getCode());
        actionListDs.setLibelle(action.getLibelle());
//        actionListDs.setTypeProfil(action.getTypeProfil());
        return actionListDs;
    }

    public Action assembleActionFromDs(ActionListDs actionListDs) {
        Action action = new Action();
        action.setId(actionListDs.getId());
        action.setCode(actionListDs.getCode());
        action.setLibelle(actionListDs.getLibelle());
//        action.setTypeProfil(actionListDs.getTypeProfil());
        return action;
    }

    //
    public List<ActionListDs> createListActionListDs(Set<Action> actions) {
        if (actions == null)
            return Collections.emptyList();
        List<ActionListDs> dtos = new ArrayList<>();
        for (Action action : actions) {
            dtos.add(assembleEntityToDs(action));
        }
        return dtos;
    }

    public Set<Action> createSetAction(List<ActionListDs> actionListDs) {
        if (actionListDs == null) {
            return Collections.emptySet();
        }
        return actionListDs.stream()
                .map(actionDs -> actionService.findById(actionDs.getId()))
                .collect(Collectors.toSet());
    }
}
