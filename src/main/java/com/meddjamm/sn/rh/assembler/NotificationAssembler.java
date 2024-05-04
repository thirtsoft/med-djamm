package com.meddjamm.sn.rh.assembler;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.service.UtilisateurService;
import com.meddjamm.sn.rh.entity.Notification;
import com.meddjamm.sn.rh.remote.model.NotificationDs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationAssembler {

    private final UtilisateurService utilisateurService;

    public NotificationAssembler(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public List<NotificationDs> assembleEntitiesFrom(List<Notification> notificationList) {
        return notificationList.stream().map(this::assembleEntityToDs).toList();
    }

    public NotificationDs assembleEntityToDs(Notification notification) {
        NotificationDs notificationDs = new NotificationDs();
        if (notification.getId() != null) notificationDs.setId(notification.getId());
        notificationDs.setCreatedDate(notification.getCreatedDate());
        notificationDs.setMessage(notification.getMessage());
        notificationDs.setCreatedBy(notification.getCreatedBy());
        if (notification.getCreatedBy() != null) {
            Utilisateur utilisateur = utilisateurService.findUserById(notification.getCreatedBy());
            String nomAgent = utilisateur.getPrenom() + ' ' + utilisateur.getNom();
            notificationDs.setNomCompletAgent(nomAgent);
        }
        return notificationDs;
    }

    public Notification assembleNotificationFromDs(NotificationDs notificationDs) {
        Notification notification = new Notification();
        if (notification.getId() != null) notification.setId(notificationDs.getId());
        notification.setCreatedDate(notificationDs.getCreatedDate());
        notification.setMessage(notificationDs.getMessage());
        notification.setCreatedBy(notificationDs.getCreatedBy());
        return notification;
    }
}
