package com.meddjamm.sn.rh.remote.controller;

import com.meddjamm.sn.rh.assembler.NotificationAssembler;
import com.meddjamm.sn.rh.entity.Notification;
import com.meddjamm.sn.rh.remote.controller.api.NotificationApi;
import com.meddjamm.sn.rh.remote.model.NotificationDs;
import com.meddjamm.sn.rh.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController implements NotificationApi {

    private final NotificationService notificationService;

    private final NotificationAssembler notificationAssembler;

    public NotificationController(NotificationService notificationService,
                                  NotificationAssembler notificationAssembler) {
        this.notificationService = notificationService;
        this.notificationAssembler = notificationAssembler;
    }

    @Override
    public ResponseEntity<NotificationDs> creerNotification(NotificationDs notificationDs) {
        Notification notification = notificationAssembler.assembleNotificationFromDs(notificationDs);
        notificationService.saveNotification(notification);
        return new ResponseEntity<>(notificationAssembler.assembleEntityToDs(notification), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NotificationDs> updateNotification(Long id, NotificationDs notificationDs) throws Exception {
        Notification notification = notificationAssembler.assembleNotificationFromDs(notificationDs);
        notificationService.updateNotification(id, notification);
        return new ResponseEntity<>(notificationAssembler.assembleEntityToDs(notification), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NotificationDs> findById(Long id) {
        return new ResponseEntity<>(notificationAssembler.assembleEntityToDs(
                notificationService.findById(id)
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<NotificationDs>> findAllNotifications() {
        return new ResponseEntity<>(notificationAssembler.assembleEntitiesFrom(
                notificationService.findAllNotifications()
        ), HttpStatus.OK);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationService.deleteNotification(id);
    }

    @Override
    public long countNotify() {
        return notificationService.countNumberOfActiveMessage();
    }

    @Override
    public ResponseEntity<List<NotificationDs>> findTop6Notifications() {
        return new ResponseEntity<>(notificationAssembler.assembleEntitiesFrom(
                notificationService.findTop6ByOrderByCreatedDateDesc()
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<NotificationDs>> findTop20Notifications() {
        return new ResponseEntity<>(notificationAssembler.assembleEntitiesFrom(
                notificationService.findTop20ByOrderByCreatedDateDesc()
        ), HttpStatus.OK);
    }
}
