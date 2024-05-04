package com.meddjamm.sn.rh.services.Impl;

import com.meddjamm.sn.rh.entity.Notification;
import com.meddjamm.sn.rh.repository.NotificationRepository;
import com.meddjamm.sn.rh.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void saveNotification(Notification notification) {
        notification.setActif(true);
        notification.setCreatedDate(new Date());
        notificationRepository.save(notification);
    }

    @Override
    public void updateNotification(Long id, Notification notification) throws Exception {
        if (!notificationRepository.existsById(id)) {
            log.info("Notification that id is " + id + "not found");
        }
        notification.setId(id);
        notificationRepository.save(notification);

    }

    @Override
    public Notification findById(Long id) {
        return notificationRepository.findNotificationById(id);
    }

    @Override
    public List<Notification> findAllNotifications() {
        return notificationRepository.findAllNotifications();
    }

    @Override
    public void deleteNotification(Long id) {
        Notification notification = findById(id);
        notification.setActif(false);
        notificationRepository.save(notification);
    }

    @Override
    public long countNumberOfActiveMessage() {
        return notificationRepository.countNotify();
    }

    @Override
    public List<Notification> findTop6ByOrderByCreatedDateDesc() {
        return notificationRepository.findTop6ByOrderByCreatedDateDesc();
    }

    @Override
    public List<Notification> findTop20ByOrderByCreatedDateDesc() {
        return notificationRepository.findTop20ByOrderByCreatedDateDesc();
    }
}