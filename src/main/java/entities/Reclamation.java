package entities;

public class Reclamation {
    private int id;
    private int id_user;
    private String titre;
    private String description;

    private String etat;
    private String date;
    private String nom_user;

    private String prenom_user;
    public Reclamation(int id, int id_user, String titre, String description, String etat, String date, String nom_user, String prenom_user) {
        this.id = id;
        this.id_user = id_user;
        this.titre = titre;
        this.description = description;
        this.etat = etat;
        this.date = date;
        this.nom_user=nom_user;
        this.prenom_user=prenom_user;
    }
    public Reclamation(int id, int id_user, String titre, String description, String etat, String date) {
        this.id = id;
        this.id_user = id_user;
        this.titre = titre;
        this.description = description;
        this.etat = etat;
        this.date = date;

    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", etat='" + etat + '\'' +
                ", nom='" + nom_user + '\'' +
                ", prenom='" + prenom_user + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public Reclamation(int id) {
        this.id = id;
    }

    public Reclamation(int id_user,String titre, String description,String etat) {
        this.id_user=id_user;
        this.titre = titre;
        this.description = description;
        this.etat=etat;
    }

    public Reclamation( int id_user,String titre, String description) {
        this.id_user=id_user;
        this.titre = titre;
        this.description = description;
    }
}
