package com.meddjamm.sn.rh.services;

import com.meddjamm.sn.rh.entity.Notification;

import java.util.List;

public interface NotificationService {

    void saveNotification(Notification notification);

    void updateNotification(Long id, Notification notification) throws Exception;

    Notification findById(Long id);

    List<Notification> findAllNotifications();

    void deleteNotification(Long id);

    long countNumberOfActiveMessage();

    List<Notification> findTop6ByOrderByCreatedDateDesc();

    List<Notification> findTop20ByOrderByCreatedDateDesc();
}
