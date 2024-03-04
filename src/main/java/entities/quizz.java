package entities;

import java.util.ArrayList;
import java.util.List;

public class Quizz {
    private int id_quizz;
    private Question id_question;
    private String sujet;
    private String description;
    private List<Question> questions;

    public Quizz(int id_quizz, Question id_question, String sujet, String description, List<Question> questions) {
        this.id_quizz = id_quizz;
        this.id_question = id_question;
        this.sujet = sujet;
        this.description = description;
        this.questions = questions;
    }

    public Quizz(Question id_question, String sujet, String description) {
        this.id_question = id_question;
        this.sujet = sujet;
        this.description = description;
    }

    public Quizz(int id_quizz, Question id_question, String sujet, String description) {
        this.id_quizz = id_quizz;
        this.id_question = id_question;
        this.sujet = sujet;
        this.description = description;
    }

    public Quizz(int id_quizz, String sujet, String description) {
        this.id_quizz = id_quizz;
        this.sujet = sujet;
        this.description = description;
    }

    public Quizz(String sujet, String description) {
        this.sujet = sujet;
        this.description = description;
    }

    public Quizz() {
        this.questions = new ArrayList<>();
    }

    public int getId_quizz() {
        return id_quizz;
    }

    public void setId_quizz(int id_quizz) {
        this.id_quizz = id_quizz;
    }

    public Question getId_question() {
        return id_question;
    }

    public void setId_question(Question id_question) {
        this.id_question = id_question;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Quizz{" +
                "id_quizz=" + id_quizz +
                ", id_question=" + id_question +
                ", sujet='" + sujet + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                '}';
    }
}
