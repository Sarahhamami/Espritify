package entities;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private int id;
    private String intitule;
    private String logo;
    private String emailClub;
    private String pageFb;
    private String pageInsta;
    private List<Evenement> evenements = new ArrayList<>();


    public Club(int id, String intitule, String emailClub, String logo, String pageFb, String pageInsta) {
        this.id = id;
        this.logo = logo;
        this.intitule = intitule;
        this.emailClub = emailClub;
        this.pageFb = pageFb;
        this.pageInsta = pageInsta;
    }

    public Club(String intitule,String emailClub, String logo,  String pageFb, String pageInsta) {
        this.intitule = intitule;
        this.emailClub = emailClub;
        this.logo = logo;
        this.pageFb = pageFb;
        this.pageInsta = pageInsta;
    }

    public Club() {

    }

    public Club(String intitule) {
        this.intitule = intitule;
    }

    /*public Club(int id, String intitule,String emailClub, String logo, String pageFb, String pageInsta) {
        this.id = id;
        this.logo = logo;
        this.intitule = intitule;
        this.emailClub = emailClub;
        this.pageFb = pageFb;
        this.pageInsta = pageInsta;
    }*/



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmailClub() {
        return emailClub;
    }

    public void setEmailClub(String emailClub) {
        this.emailClub = emailClub;
    }

    public String getPageFb() {
        return pageFb;
    }

    public void setPageFb(String pageFb) {
        this.pageFb = pageFb;
    }

    public String getPageInsta() {
        return pageInsta;
    }

    public void setPageInsta(String pageInsta) {
        this.pageInsta = pageInsta;
    }

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void addEvenement(Evenement evenement) {
        evenements.add(evenement);
    }
}
