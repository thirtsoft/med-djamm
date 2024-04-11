package com.meddjamm.sn.dossiermedical.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.dossiermedical.entity.Discussion;
import com.meddjamm.sn.dossiermedical.remote.model.DiscussionDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiscussionAssembler {

    private final UtilisateurService utilisateurService;

    public DiscussionAssembler(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public List<DiscussionDs> assembleEntitiesFrom(List<Discussion> discussions) {
        return discussions.stream().map(this::assembleEntityToDs).toList();
    }

    public List<Discussion> assembleEntitiesFromDs(List<DiscussionDs> discussions) {
        return discussions.stream().map(this::assembleDiscussionFromDs).toList();
    }

    public DiscussionDs assembleEntityToDs(Discussion discussion) {
        DiscussionDs discussionDs = new DiscussionDs();
        if (discussionDs.getId() == null)
            return null;
        discussionDs.setId(discussion.getId());
        discussionDs.setResume(discussion.getResume());
        discussionDs.setActif(discussion.isActif());
        discussionDs.setCircuitPatientId(discussion.getCircuitPatient().getId());
        if (discussion.getCreatedByUser() != null) {
            Utilisateur utilisateur = utilisateurService.findUtilisateurByMatricule(discussion.getCreatedByUser());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            discussionDs.setNomCompletAgent(nomAgent);
        }
        return discussionDs;
    }

    public Discussion assembleDiscussionFromDs(DiscussionDs discussionDs) {
        Discussion discussion = new Discussion();
        if (discussionDs.getId() != null)
            discussion.setId(discussionDs.getId());
        discussion.setResume(discussionDs.getResume());
        discussion.setActif(discussionDs.isActif());
        discussion.setCircuitPatientId(discussionDs.getCircuitPatientId());
        return discussion;
    }
}
