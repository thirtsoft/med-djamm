package com.meddjamm.sn.rh.assembler;

import com.meddjamm.sn.rh.entity.Action;
import com.meddjamm.sn.rh.remote.model.ActionListDs;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ActionAssembler {

    public List<ActionListDs> assembleEntitiesFrom(List<Action> actions) {
        return actions.stream().map(this::assembleEntityToDs).toList();
    }

    public ActionListDs assembleEntityToDs(Action action) {
        ActionListDs actionListDs = new ActionListDs();
        actionListDs.setId(action.getId());
        actionListDs.setCode(action.getCode());
        actionListDs.setLibelle(action.getLibelle());
        actionListDs.setTypeProfil(action.getTypeProfil());
        return actionListDs;
    }

    public Action assembleActionFromDs(ActionListDs actionListDs) {
        Action action = new Action();
        action.setId(actionListDs.getId());
        action.setCode(actionListDs.getCode());
        action.setLibelle(actionListDs.getLibelle());
        action.setTypeProfil(actionListDs.getTypeProfil());
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
        if (actionListDs == null)
            return null;
        Set<Action> actions = new HashSet<>();
        for (ActionListDs dto : actionListDs)
            if (dto != null)
                actions.add(assembleActionFromDs(dto));
        return actions;
    }
}
