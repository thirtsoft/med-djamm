package com.meddjamm.sn.rh.piecejointe;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class PiecesJointesDs {

    private Long typeDocumentId; //type du document entré

    private String typeDocumentLibelle;

    private Long objectId; //ID de l'objet concerné

    private String dossier;

    private Long id;

    private String nomFichier;

    private String nomTechnique;

    private Date dateCreation;

    private int actif;

    private boolean v1;

    private boolean docPme; //v1 document enregistré dans PME true / acc false

    private boolean pdf;

    private boolean img;

    private boolean xls;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private byte[] content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long idDocument;

    public PiecesJointesDs() {
        super();
        this.dateCreation = new Date();
        this.actif = 1;
    }

    public Long getTypeDocumentId() {
        return typeDocumentId;
    }

    public void setTypeDocumentId(Long typeDocumentId) {
        this.typeDocumentId = typeDocumentId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getDossier() {
        return dossier;
    }

    public void setDossier(String dossier) {
        this.dossier = dossier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public String getNomTechnique() {
        return nomTechnique;
    }

    public void setNomTechnique(String nomTechnique) {
        this.nomTechnique = nomTechnique;
        if (nomTechnique != null) {
            if (nomTechnique.endsWith(".pdf"))
                pdf = true;
            else if (nomTechnique.endsWith(".png") || nomTechnique.endsWith(".jpg") || nomTechnique.endsWith(".jpeg"))
                img = true;
        }
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getActif() {
        return actif;
    }

    public void setActif(int actif) {
        this.actif = actif;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Long getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Long idDocument) {
        this.idDocument = idDocument;
    }

    public String getTypeDocumentLibelle() {
        return typeDocumentLibelle;
    }

    public void setTypeDocumentLibelle(String typeDocumentLibelle) {
        this.typeDocumentLibelle = typeDocumentLibelle;
    }

    public boolean isV1() {
        return v1;
    }

    public void setV1(boolean v1) {
        this.v1 = v1;
    }

    public boolean isPdf() {
        return pdf;
    }

    public void setPdf(boolean pdf) {
        this.pdf = pdf;
    }

    public boolean isDocPme() {
        return docPme;
    }

    public void setDocPme(boolean docPme) {
        this.docPme = docPme;
    }

    public boolean isImg() {
        return img;
    }

    public void setImg(boolean img) {
        this.img = img;
    }

    public boolean isXls() {
        return xls;
    }

    public void setXls(boolean xls) {
        this.xls = xls;
    }
}