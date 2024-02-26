package test.gestion_quizz;

import entities.Question;
import entities.Quizz;
import entities.Reponse;
import services.QuestionServices;
import services.QuizzService;
import services.ReponseServices;

public class Main {


    public static void main(String[] args) {

        //reponse
        ReponseServices rs=new ReponseServices();
        Reponse rep= new Reponse("what is your name");
        Reponse rep1= new Reponse(8,"how are you");
        Reponse rep2= new Reponse(2,"what is your dsdqsd");
        //rs.add(rep);
        //rs.delete(rep1);
        //rs.update(rep1);
        //rs.readAll().forEach(System.out::println);
        //Reponse r= rs.readById(6);
        //System.out.println(r);

        //Question

        QuestionServices qs= new QuestionServices();
        Question q1= new Question("programmation",rep1,"true",1);
        Question q2= new Question(7,"dsfsdfsdfsdf",rep2,"false",2);

        //qs.add(q1);
        //qs.delete(q2);
        //qs.update(q2);
        //qs.readAll().forEach(System.out::println);
        //Question q=qs.readById(7);
        //System.out.println(q);

        QuizzService qz= new QuizzService();
        Quizz qz1= new Quizz(3,"quizz de tla","exercice de tla",q2);
        Quizz qz2= new Quizz(2,"quizz de mathematique","description",q2);

        //qz.add(qz1);
        //qz.delete(qz2);
        //qz.update(qz1);
        qz.readAll().forEach(System.out::println);
        //Quizz qu= qz.readById(4);
        //System.out.println(qu);

        //Quizz q= qz.read(1);
        //System.out.println(q);

    }
}