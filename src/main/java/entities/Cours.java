package entities;

public class Cours {
    private int id;
    private String titre;
    private boolean etat;

    public Cours() {}

    public Cours(int id, String titre, boolean etat) {
        this.id = id;
        this.titre = titre;
        this.etat = etat;
    }

    public Cours(String titre, boolean etat) {
        this.titre = titre;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", etat=" + etat +
                '}';
    }
}
