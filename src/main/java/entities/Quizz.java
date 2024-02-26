package entities;
import entities.Question;

import java.util.ArrayList;
import java.util.List;

public class Quizz {
    private int id_quizz;
    private String sujet;
    private String descript;
    private Question question;

    private List<Question> questions;


    public Quizz() {
        this.questions= new ArrayList<>();
    }

    public Quizz(int id_quizz, String sujet, String descript, Question question) {
        this.id_quizz = id_quizz;
        this.sujet = sujet;
        this.descript = descript;
        this.question = question;
    }

    public Quizz(String sujet, String descript, Question question) {
        this.sujet = sujet;
        this.descript = descript;
        this.question = question;
    }

    public Quizz(int idQuizz, String sujet, String descript) {
        this.id_quizz = idQuizz;
        this.sujet = sujet;
        this.descript = descript;
    }

    public int getId_quizz() {
        return id_quizz;
    }

    public void setId_quizz(int id_quizz) {
        this.id_quizz = id_quizz;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Quizz{" + "id_quizz=" + id_quizz + ", sujet='" + sujet + ", descript='" + descript + ", question=" + question + '}';
    }
}
