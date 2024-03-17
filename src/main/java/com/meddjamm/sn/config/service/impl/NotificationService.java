package com.meddjamm.sn.config.service.impl;

import com.meddjamm.sn.config.entity.Validation;
import com.meddjamm.sn.utils.UtilString;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {

    JavaMailSender javaMailSender;

    public void envoyerEmailDeValidation(Validation validation, String url) throws MessagingException {
        String message = "</p>Bonjour" + validation.getUtilisateur().getPrenom() + ",</p>";
        message += "<p> Cliquer sur le lien ci-dessous pour valider votre compte:</p>";
        String urlDeVerification = url + "/med-dalaljamm/v1/utilisateur/activation?code=" + validation.getCode();
        message += "<h3><a href=\"" + urlDeVerification + "\">VERIFIER</a></h3>";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom(UtilString.getCurrentUser());
        helper.setTo(validation.getUtilisateur().getEmail());
        helper.setSubject("Votre code d'activation");
        helper.setText(message, true);

        javaMailSender.send(mimeMessage);
    }
}