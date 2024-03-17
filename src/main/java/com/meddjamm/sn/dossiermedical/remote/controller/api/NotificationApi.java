package com.meddjamm.sn.dossiermedical.remote.controller.api;

import com.meddjamm.sn.dossiermedical.remote.model.NotificationDs;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.meddjamm.sn.utils.ApiUrlAccess.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/notification")
public interface NotificationApi {

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<NotificationDs> creerNotification(@RequestBody NotificationDs notificationDs);

    @PutMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<NotificationDs> updateNotification(@PathVariable Long id, @RequestBody NotificationDs notificationDs) throws Exception;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<NotificationDs> findById(@PathVariable Long id);

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<NotificationDs>> findAllNotifications();

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteNotification(@PathVariable Long id);

    @GetMapping(value = "/count-notify")
    long countNotify();

    @GetMapping(value = "/top6-notify", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<NotificationDs>> findTop6Notifications();

    @GetMapping(value = "/top20-notify", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<NotificationDs>> findTop20Notifications();

}
