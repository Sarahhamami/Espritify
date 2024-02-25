package entities;

public class Reponse {
    private int id_rep;
    private String contenu;

    public Reponse() { }

    public Reponse(int id_rep, String contenu) {
        this.id_rep = id_rep;
        this.contenu = contenu;
    }

    public Reponse(String contenu) {
        this.contenu = contenu;
    }

    public int getId_rep() {
        return id_rep;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId_rep(int id_rep) {
        this.id_rep = id_rep;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "reponse{" + "id_rep=" + id_rep + ", contenu='" + contenu + '\'' + '}';
    }
}