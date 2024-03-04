package entities;

public class Message {
    private int id;
    private int id_user;
    private int id_conv;
    private String date;

    private String nom_commentor;

    public String getNom_commentor() {
        return nom_commentor;
    }

    public void setNom_commentor(String nom_commentor) {
        this.nom_commentor = nom_commentor;
    }

    public String getPrenom_commentor() {
        return prenom_commentor;
    }

    public void setPrenom_commentor(String prenom_commentor) {
        this.prenom_commentor = prenom_commentor;
    }

    private String prenom_commentor;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String description;


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

    public int getId_conv() {
        return id_conv;
    }

    public void setId_conv(int id_conv) {
        this.id_conv = id_conv;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Message( int id_user, int id_conv, String description) {
        this.id_user = id_user;
        this.id_conv = id_conv;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", id_conv=" + id_conv +
                ", date='" + date + '\'' +
                ", nom_commentor='" + nom_commentor + '\'' +
                ", prenom_commentor='" + prenom_commentor + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Message(int id, int id_user, int id_conv, String description, String date) {
        this.id=id;
        this.id_user = id_user;
        this.id_conv = id_conv;
        this.date = date;
        this.description = description;


    }
    public Message(int id, int id_user, int id_conv, String description, String date,String nom,String prenom) {
        this.id=id;
        this.id_user = id_user;
        this.id_conv = id_conv;
        this.date = date;
        this.description = description;
        this.nom_commentor=nom;
        this.prenom_commentor=prenom;


    }

    public Message(int id) {
        this.id = id;
    }
}
