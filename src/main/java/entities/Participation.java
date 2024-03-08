package entities;

import java.sql.Date;

public class Participation {
    private int id_user;
    private int id_evenement;


    private String nomUtilisateur;
    private String emailUtilisateur;
    private String titreEvenement;
    private String lieuEvenement;
    private Date dateDebut;
    private Date dateFin;

    public Participation(String nomUtilisateur, String emailUtilisateur, String titreEvenement, String lieuEvenement, Date dateDebut, Date dateFin) {
      //  this.id_user = id_user;
       // this.id_evenement = id_evenement;
        this.nomUtilisateur = nomUtilisateur;
        this.emailUtilisateur = emailUtilisateur;
        this.titreEvenement = titreEvenement;
        this.lieuEvenement = lieuEvenement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public String getTitreEvenement() {
        return titreEvenement;
    }

    public void setTitreEvenement(String titreEvenement) {
        this.titreEvenement = titreEvenement;
    }

    public String getLieuEvenement() {
        return lieuEvenement;
    }

    public void setLieuEvenement(String lieuEvenement) {
        this.lieuEvenement = lieuEvenement;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
