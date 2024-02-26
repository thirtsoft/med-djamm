package com.meddjamm.sn.utils;

import java.text.DecimalFormat;

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

    //
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

    //

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
    //

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

    //
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

}
