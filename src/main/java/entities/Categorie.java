package entities;

public class Categorie {
    private int id;
    private String type;

    public Categorie() {}

    public Categorie(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Categorie(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "categorie{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
