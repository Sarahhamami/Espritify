package entities;

public class Conversation {
    private int id;
    private int id_user;
    private String titre;
    private String description;
    private String date;
    private String nom_user;
    private String prenom_user;

    private int likes;

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
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

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", nom_user='" + nom_user + '\'' +
                ", prenom_user='" + prenom_user + '\'' +
                ", likes=" + likes +
                '}';
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

    public Conversation( int id,String titre, String description) {
        this.id=id;
        this.titre = titre;
        this.description = description;

    }

    public Conversation(int id) {
        this.id = id;
    }

    public Conversation(int id, int id_user, String titre, String description, String date) {
        this.id = id;
        this.id_user = id_user;
        this.titre = titre;
        this.description = description;
        this.date = date;
    }
    public Conversation(int id, int id_user, String titre, String description, String date,String nom_user,String prenom_user,int likes) {
        this.id = id;
        this.id_user = id_user;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nom_user=nom_user;
        this.prenom_user=prenom_user;
        this.likes=likes;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
