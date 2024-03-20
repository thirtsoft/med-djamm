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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    //    private final UtilisateurService utilisateurService;
    private final JavaMailSender mailSender;
    private final ValidationService validationService;
    private final PasswordEncoder passwordEncoder;

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
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("Click de verification de votre compte :  {}", url);
    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email de Verification";
        String senderName = "Service Enregistrement";
        String mailContent = "<p> Bonjour, " + utilisateur.getPrenom() + ", </p>" +
                "<p>Merci de nous avoir rejoint," + " <br>" +
                "Cliquer sur le lien ci-dessous pour valider votre compte.</p>" +
                "<a href=\"" + url + "\">Vérifier vos emails pour activer votre compte</a>" +
                "<p>Votre mot de passe: " + utilisateur.getMotdepasseprecedent() + "</p>" +
                "<p> Cordialement <br> Service d'enregistrement</p>";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(configEmail, senderName);
        messageHelper.setTo(utilisateur.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }

    public void sendPasswordResetVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Password Reset Request Verification";
        String senderName = "Service Enregistrement";
        String mailContent = "<p> Hi, " + utilisateur.getPrenom() + ", </p>" +
                "<p><b>You recently requested to reset your password,</b>" + "" +
                "Please, follow the link below to complete the action.</p>" +
                "<a href=\"" + url + "\">Reset password</a>" +
                "<p> Users Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("dailycodework@gmail.com", senderName);
        messageHelper.setTo(utilisateur.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}