package org.example;

import entities.Utilisateur;
import service.UtilisateurService;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException {

        Utilisateur u2=new Utilisateur(56,"ahmed","hmid");
        Utilisateur u1=new Utilisateur("jawed","chlibi","jawed@","joujou",456987,"3a","etudiant");
        UtilisateurService us=new UtilisateurService();
        System.out.println(us.displayByid(56));
    }
}