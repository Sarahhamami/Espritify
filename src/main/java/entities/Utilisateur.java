package entities;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String image;
    private int rank;
    private int score;
    private String nom_societe;
    private String niveau;
    private String role;
    private int tel;
private String mdp;
    public Utilisateur() {

    }

    public Utilisateur(String nom, String prenom, String email, String mdp, int id,int tel, String niveau, String image, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.niveau = niveau;
        this.role = role;
        this.mdp = mdp;
        this.id = id;
        this.image = image;
        this.tel = tel;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }



    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public int getRank() {
        return rank;
    }

    public int getScore() {
        return score;
    }

    public String getNom_societe() {
        return nom_societe;
    }

    public String getNiveau() {
        return niveau;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setNom_societe(String nom_societe) {
        this.nom_societe = nom_societe;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", rank=" + rank +
                ", score=" + score +
                ", nom_societe='" + nom_societe + '\'' +
                ", niveau='" + niveau + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public Utilisateur(String nom, String prenom, String email, String mdp, int id, String niveau, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.niveau = niveau;
        this.role = role;
        this.mdp = mdp;
        this.id = id;

    }

    public Utilisateur(int id,String nom,String prenom) {
        this.id = id;
        this.nom=nom;
        this.prenom=prenom;
    }

    public Utilisateur(String nom, String prenom, String email, String niveau, int tel, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.niveau = niveau;
        this.tel = tel;
        this.mdp = mdp;
    }

    public Utilisateur(int id) {
        this.id = id;
    }
    public Utilisateur(String role) {
        this.role = role;
    }
}
