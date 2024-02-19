package test.workshop;
import entites.Quizz;
import services.QuizzService;
import utils.dbconnection;
public class Main {


    public static void main(String[] args) {

        Quizz q1=new Quizz("whats your name");
        Quizz q2=new Quizz("whats your age");
        Quizz q3=new Quizz("whats your number");

        Quizz q5=new Quizz(4,"where are you going");
        Quizz q=new Quizz();

        QuizzService qs=new QuizzService();

        //ajout
        //qs.add(q1);
        //qs.add(q2);
        //qs.add(q3);
        qs.readAll().forEach(System.out::println);

        //supprim
        System.out.println("delete:");
        //qs.delete(q5);

        //update
        System.out.println("update:");
        qs.update(q5);
        qs.readAll().forEach(System.out::println);

        //recherche
        System.out.println("recherche:");
        q=qs.readById(4);
        System.out.println(q);

    }
}