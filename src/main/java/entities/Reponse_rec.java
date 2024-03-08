package entities;

public class Reponse_rec {
    private int id;
    private String titre_rec;

    private int id_rec;
    private String titre;
    private String desc;
    private String date;

    public Reponse_rec(int id) {
        this.id=id;
    }

    public String getTitre_rec() {
        return titre_rec;
    }

    public void setTitre_rec(String titre_rec) {
        this.titre_rec = titre_rec;
    }

    public Reponse_rec(int id_rec, String titre, String desc) {
        this.id_rec = id_rec;
        this.titre = titre;
        this.desc = desc;
    }

    public Reponse_rec( String titre,int id, String desc) {
        this.titre = titre;
        this.id = id;
        this.desc = desc;
    }
    public Reponse_rec(int id, int id_rec, String titre, String desc, String date) {
        this.id = id;
        this.id_rec = id_rec;
        this.titre = titre;
        this.desc = desc;
        this.date = date;
    }

    public Reponse_rec(int id, int id_rec,String titre_rec, String titre, String desc, String date) {
        this.id = id;
        this.id_rec = id_rec;
        this.titre = titre;
        this.desc = desc;
        this.date = date;
        this.titre_rec=titre_rec;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Reponse_rec() {
    }

    @Override
    public String toString() {
        return "Reponse_rec{" +
                "id=" + id +
                ", titre_rec='" + titre_rec + '\'' +
                ", id_rec=" + id_rec +
                ", titre='" + titre + '\'' +
                ", desc='" + desc + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

