package com.meddjamm.sn.dossiermedical.repository;

import com.meddjamm.sn.dossiermedical.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT DISTINCT p from Notification p where p.id=:id and p.actif=1")
    Notification findNotificationById(@Param("id") Long id);

    @Query("SELECT DISTINCT p from Notification p where p.actif=1")
    List<Notification> findAllNotifications();

    @Query("SELECT COUNT(p) FROM Notification p WHERE p.actif=1")
    long countNotify();

    @Query("select n from Notification n where n.actif=1 order by n.createdDate desc ")
    List<Notification> findTop6ByOrderByCreatedDateDesc();

    @Query("select n from Notification n where n.actif=1 order by n.createdDate desc ")
    List<Notification> findTop20ByOrderByCreatedDateDesc();

}
