package com.meddjamm.sn.rh.piecejointe;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileUtilsService {

    public String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else
            return "";
    }

    public String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else
            return "";
    }

    public boolean isImg(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (ext.equals("png") || ext.equals("jpg") || ext.equals("jpeg") || ext.equals("gif"))
                return true;
        }
        return false;
    }

    public boolean isPdf(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (ext.equals("pdf"))
                return true;
        }
        return false;
    }

    public void supprimerDossiersVides(String repertoire, String nom_fichier) {
        String chemin = repertoire + "/" + nom_fichier;
        File fichier = new File(chemin);
        if (fichier.exists())
            fichier.delete();
        chemin = repertoire;
        fichier = new File(chemin);
        if (fichier.listFiles() != null)
            if (fichier.listFiles().length == 0)
                fichier.delete();
    }

    public String getExtentionFichierFin(String nomFichier) {
        if (nomFichier.endsWith(".docx"))
            return ".docx";
        else if (nomFichier.endsWith(".doc"))
            return ".doc";
        else if (nomFichier.endsWith(".xls"))
            return ".xls";
        else if (nomFichier.endsWith(".xlsx"))
            return ".xlsx";
        return "";
    }
}
