package org.example;
import entities.Cours;
import entities.Categorie;
import services.CategorieService;
import services.CoursService;

public class MainCategorie {
    public static void main(String[] args) {
        Categorie cat1=new Categorie(5,"ons");
        Categorie cat=new Categorie();
        CategorieService cats=new CategorieService();

        //cats.add(cat1);
        //cats.delete(cat1);
        //cats.update(cat1);
        //cats.readAll().forEach(System.out::println);
        //cat=cats.readByid(4);
        //System.out.println(cat);

        Cours c1=new Cours("java",true,"test",cat1);
        Cours c2=new Cours(3,"python",true,"test",cat1);
        Cours c=new Cours();
        CoursService cs=new CoursService();

        //cs.add(c2);
        //cs.delete(c2);
        //cs.update(c2);
        cs.readAll().forEach(System.out::println);
        //c=cs.readById(1);
        //System.out.println(c);
    }
}
