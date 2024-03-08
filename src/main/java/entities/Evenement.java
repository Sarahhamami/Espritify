package entities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Evenement {
    private int id;
    private int id_club;
    private String intitule;
    private String titre;
    private String description;
    private String lieu;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Evenement(int id, int id_club, String titre, String description, String lieu, LocalDate dateDebut, LocalDate dateFin) {
        this.id = id;
        this.id_club = id_club;
        this.titre = titre;
        this.description = description;
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFin =dateFin;
    }

    public Evenement(int id_club, String titre, String description, String lieu, LocalDate dateDebut, LocalDate dateFin) {
        this.id_club = id_club;
        this.titre = titre;
        this.description = description;
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }


    public Evenement(String titre, String description, String lieu, LocalDate dateDebut, LocalDate dateFin) {
        this.titre = titre;
        this.description = description;
        this.lieu = lieu;
        this.dateDebut = dateDebut ;//Date.from(dateDebut.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.dateFin = dateFin;//Date.from(dateFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }



    public Evenement(int id, int id_club, String titre, String description, String lieu) {
        this.id = id;
        this.id_club = id_club;
        this.titre = titre;
        this.description = description;
        this.lieu = lieu;

    }


    public Evenement() {

    }



   /* public Evenement(int eventId, String titre, String description, String lieu, LocalDate dateDebut, LocalDate dateFin) {
        this.id = eventId;
        this.titre = titre;
        this.description = description;
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;

    }*/



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_club() {
        return id_club;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", id_club=" + id_club +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", lieu='" + lieu + '\'' +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateFin='" + dateFin + '\'' +
                '}';
    }
}


