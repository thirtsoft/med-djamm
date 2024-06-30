package com.meddjamm.sn.config.event.listener;

import com.meddjamm.sn.config.entity.Utilisateur;
import com.meddjamm.sn.config.event.RegistrationCompleteEvent;
import com.meddjamm.sn.config.service.impl.ValidationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final JavaMailSender mailSender;
    private final ValidationService validationService;

    @Value("${spring.mail.username}")
    private String configEmail;
    private Utilisateur utilisateur;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        utilisateur = event.getUtilisateur();

        String token = UUID.randomUUID().toString();
        validationService.enregistrerCode(utilisateur, token);

        String url = event.getUrl() + "/med-dalaljamm/v1/utilisateur/activation?code=".concat(token);
        try {
            sendVerificationEmail(url, event.getDefaultPassword());
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("Click de verification de votre compte :  {}", url);
    }

    private void sendVerificationEmail(String url, String defaultPassword) throws MessagingException, UnsupportedEncodingException {
        String subject = "Invitation sur l'application SNAPPLIMED";
        String senderName = "Service Médecine";
        String mailContent = "<p> Bonjour, M(e)" + utilisateur.getPrenom() + " </p>" +
                "<p>Le service Médecine Interne l’hôpital Dalal-Jamm est heureux de vous compter parmi ses collaborateurs.<br>" +
                "Vous pouvez cliquer sur le lien ci-dessous pour activer votre compte.</p>" +
                "<a href=\"" + url + "\">activer votre compte</a>" +
                "<p>Votre mot de passe: " + defaultPassword + "</p>" +
                "<p> Cordialement <br> Service Médecine Dalal-Jamm</p>";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(configEmail, senderName);
        messageHelper.setTo(utilisateur.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }

    public void sendPasswordResetVerificationEmail(Utilisateur utilisateur, String newPassword) throws MessagingException, UnsupportedEncodingException {
        String subject = "Demande de réinitialisation de mot de passe";
        String senderName = "Service Médecine";
        String mailContent = "<p> Bonjour, M(e) " + utilisateur.getPrenom() + " </p>" +
                "<p><b>Vous avez récemment demandé la réinitialisation de votre mot de passe,</b></p>" +
                "<p>Votre nouveau mot de passe est : " + newPassword + "</p>" +
                "<p>Cordialement <br> Service Médecine Dalal-Jamm</p>";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(configEmail, senderName);
        messageHelper.setTo(utilisateur.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}