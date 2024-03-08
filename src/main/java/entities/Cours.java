package entities;

public class Cours {
    private int id;
    private String titre;
    private boolean etat;
    private String contenu;
    private int rate;

    private Categorie categorie;

    public Cours() {}

    public Cours(int id, String titre, boolean etat, String contenu, int rate, Categorie categorie) {
        this.id = id;
        this.titre = titre;
        this.etat = etat;
        this.contenu = contenu;
        this.rate = rate;
        this.categorie = categorie;
    }

    public Cours(int id, String titre, boolean etat, String contenu, Categorie categorie) {
        this.id = id;
        this.titre = titre;
        this.etat = etat;
        this.contenu = contenu;
        this.categorie=categorie;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    public Cours(int id, String titre, boolean etat, String contenu) {
        this.id = id;
        this.titre = titre;
        this.etat = etat;
        this.contenu = contenu;
    }

    public Cours(String titre, boolean etat, String contenu, Categorie categorie) {
        this.titre = titre;
        this.etat = etat;
        this.contenu = contenu;
        this.categorie=categorie;
    }

    public Cours(String titre, boolean etat, String contenu) {
        this.titre = titre;
        this.etat = etat;
        this.contenu = contenu;
    }

    public Cours(int id, String titre, String contenu) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
    }

    public Cours(int id, String titre) {
        this.id=id;
        this.titre=titre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Categorie getcat() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", etat=" + etat +
                ", contenu='" + contenu + '\'' +
                ", categorie=" + categorie +
                '}';
    }
}
