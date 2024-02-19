package org.example;
import entities.categorie;
import services.CategorieService;
import utils.DataSources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainCategorie {
    public static void main(String[] args) {
        categorie cat1=new categorie(2,"rr");
        categorie cat=new categorie();
        CategorieService cats=new CategorieService();

        //cats.add(cat1);
        //cats.delete(cat1);
        //cats.update(cat1);
        //cats.readAll().forEach(System.out::println);
        //cat=cats.readByid(4);
        //System.out.println(cat);
    }
}
