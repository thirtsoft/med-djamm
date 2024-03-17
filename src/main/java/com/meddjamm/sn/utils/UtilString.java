package com.meddjamm.sn.utils;

import com.meddjamm.sn.config.entity.Utilisateur;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilString {

    public static String createNumeroDossierPatient(int poz) {
        String numero = "";
        DecimalFormat numFormat = new DecimalFormat("00000");
        numero = "D0SSIER_" + numFormat.format(poz);
        return numero;
    }

    public static int formatNumeroDossierPatient(String poz) {
        int numero = 0;
        String s = poz.substring(3);
        numero = Integer.parseInt(s);
        return numero;
    }

    public static String createNumeroCircuitPatient(int poz) {
        String numero = "";
        DecimalFormat numFormat = new DecimalFormat("00000");
        numero = "CIRCUIT_" + numFormat.format(poz);
        return numero;
    }

    public static int formatNumeroCircuitPatient(String poz) {
        int numero = 0;
        String s = poz.substring(3);
        numero = Integer.parseInt(s);
        return numero;
    }

    public static String createNumeroPassagePatient(int poz) {
        String numero = "";
        DecimalFormat numFormat = new DecimalFormat("00000");
        numero = "PASSAGE_" + numFormat.format(poz);
        return numero;
    }

    public static int formatNumeroPassagePatient(String poz) {
        int numero = 0;
        String s = poz.substring(3);
        numero = Integer.parseInt(s);
        return numero;
    }

    public static String createNumeroObservationClinique(int poz) {
        String numero = "";
        DecimalFormat numFormat = new DecimalFormat("00000");
        numero = "OBSERVATION_" + numFormat.format(poz);
        return numero;
    }

    public static int formatNumeroObservationClinique(String poz) {
        int numero = 0;
        String s = poz.substring(3);
        numero = Integer.parseInt(s);
        return numero;
    }

    public static String genererMatricule() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
        return "MAT_" + df.format(LocalDateTime.now());
    }

    public static String createNumeroExamenPhysique(int poz) {
        String numero = "";
        DecimalFormat numFormat = new DecimalFormat("00000");
        numero = "EXAMEN_PHYSIQUE" + numFormat.format(poz);
        return numero;
    }

    public static int formatNumeroExamenPhysique(String poz) {
        int numero = 0;
        String s = poz.substring(3);
        numero = Integer.parseInt(s);
        return numero;
    }

    public static String createNumeroDossierMedical(int poz) {
        String numero = "";
        DecimalFormat numFormat = new DecimalFormat("00000");
        numero = "DOSSIER_MEDICAL" + numFormat.format(poz);
        return numero;
    }

    public static int formatNumeroDossierMedical(String poz) {
        int numero = 0;
        String s = poz.substring(3);
        numero = Integer.parseInt(s);
        return numero;
    }

    public static String getUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return url.replace(request.getServletPath(), "");
    }

    public static String applicationUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return url.replace(request.getServletPath(), "");
    }

    public static String getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Utilisateur userPrincipal = (Utilisateur) authentication.getPrincipal();
        return userPrincipal.getUsername();
    }

    public static String message(String nom, String url, String token) {
        return "Bonjour " + nom + ",\n\nVotre nouveau compte a été crée. Veuillez ci-dessus pour valider votre compte.\n\n"
                + urlDeVerification(url, token) + "";
    }

    private static String urlDeVerification(String url, String token) {
        return url + "" + token;
    }


}
