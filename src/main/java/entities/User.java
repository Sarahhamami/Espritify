package entities;

import entities.ROLE;

public class User {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private int tel;
    private String image;
    private String rank;
    private int score;
    private String niveau ;
    private ROLE role;

    public User() {
    }

    public User(String nom) {

        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
