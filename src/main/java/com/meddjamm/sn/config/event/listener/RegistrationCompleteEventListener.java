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

import static com.meddjamm.sn.utils.ConstantDeployment.HOST_API;

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

        //  String url = event.getUrl() + "/med-dalaljamm/v1/utilisateur/activation?code=".concat(token);
        String url = HOST_API + "/med-dalaljamm/v1/utilisateur/activation?code=".concat(token);
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("Click de verification de votre compte :  {}", url);
    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Invitation sur l'application MEDICAPP";
        String senderName = "Service Médecine";
        String mailContent = "<p> Bonjour, " + utilisateur.getPrenom() + ", </p>" +
                "<p>Le Service Médecine Interne de Dalal-Jamm est heureuse de vous compter parmi ses collaborateurs.," + " <br>" +
                "Vous pouvez cliquez sur le lien ci-dessous pour activer votre compte.</p>" +
                "<a href=\"" + HOST_API + "\">activer votre compte</a>" +
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
                "<a href=\"" + HOST_API + "\">Reset password</a>" +
                "<p> Users Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(configEmail, senderName);
        messageHelper.setTo(utilisateur.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}