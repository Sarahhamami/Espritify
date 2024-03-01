package test.gestionquizz;

import Entities.Question;
import Entities.Quizz;
import Services.QuestionServices;
import Services.QuizzServices;

public class main {
    public static void main(String[] args){
        QuestionServices qs = new QuestionServices();
        QuizzServices qz = new QuizzServices();

        Question q1 = new Question("what language im using now ?","c++","css","javascript","java");
        Question q2 = new Question(1,"what language im using now ?","c++","css","javascript","java");
        Question q3 = new Question(2,"what language im using now ?","c++","html","javascript","java");

        //qs.add(q1);
        //qs.delete(q2);
        //qs.update(q3);
        //qs.readAll().forEach(System.out::println);
        //Question q = qs.readById(2);
        //System.out.println(q);

        Quizz qz1 = new Quizz(q3,"programamtion","sujet de programamtion");
        Quizz qz2 = new Quizz(2,q3,"programamtion","sujet de programamtion");
        Quizz qz3 = new Quizz(3,q3,"algorithme","sujet de programamtion");

        //qz.add(qz1);
        //qz.delete(qz2);
        //qz.update(qz3);
        //qz.readAll().forEach(System.out::println);
        //Quizz qzs = qz.readById(3);
        //System.out.println(qzs);
    }
}
