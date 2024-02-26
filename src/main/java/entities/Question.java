package entities;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int id_Que;
    private String contenu;
    private Reponse reponse;
    private String bonne_rep;
    private int numQue;

    private List<Reponse> reponses;

    public Question() {
        this.reponses= new ArrayList<>();
    }

    public Question(int id_Que, String contenu, Reponse reponse, String bonne_rep, int numQue) {
        this.id_Que = id_Que;
        this.contenu = contenu;
        this.reponse = reponse;
        this.bonne_rep = bonne_rep;
        this.numQue = numQue;
    }

    public Question(String contenu, Reponse reponse, String bonne_rep, int numQue) {
        this.contenu = contenu;
        this.reponse = reponse;
        this.bonne_rep = bonne_rep;
        this.numQue = numQue;
    }

    public int getId_Que() {
        return id_Que;
    }

    public void setId_Que(int id_Que) {
        this.id_Que = id_Que;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Reponse getReponse() {
        return reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    public String isBonne_rep() {
        return bonne_rep;
    }

    public void setBonne_rep(String bonne_rep) {
        this.bonne_rep = bonne_rep;
    }

    public int getNumQue() {
        return numQue;
    }

    public void setNumQue(int numQue) {
        this.numQue = numQue;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }
    @Override
    public String toString() {
        return "Question{" + "id_Que=" + id_Que + ", contenu='" + contenu + ", reponse=" + reponse + ", bonne_rep=" + bonne_rep + ", numQue=" + numQue + '}';
    }
}
