package Entities;

public class Question {
    private int id_question;
    private String contenu;
    private String reponse1;
    private String reponse2;
    private String reponse3;
    private String bonneReponse;

    public Question(int id_question, String contenu, String reponse1, String reponse2, String reponse3, String bonneReponse) {
        this.id_question = id_question;
        this.contenu = contenu;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.bonneReponse = bonneReponse;
    }

    public Question(String contenu, String reponse1, String reponse2, String reponse3, String bonneReponse) {
        this.contenu = contenu;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.bonneReponse = bonneReponse;
    }

    //en cas d'ajouter 3 reponse
    public Question(int id_question, String contenu, String reponse1, String reponse2, String bonneReponse) {
        this.id_question = id_question;
        this.contenu = contenu;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.bonneReponse = bonneReponse;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getReponse1() {
        return reponse1;
    }

    public void setReponse1(String reponse1) {
        this.reponse1 = reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }

    public void setReponse2(String reponse2) {
        this.reponse2 = reponse2;
    }

    public String getReponse3() {
        return reponse3;
    }

    public void setReponse3(String reponse3) {
        this.reponse3 = reponse3;
    }

    public String getBonneReponse() {
        return bonneReponse;
    }

    public void setBonneReponse(String bonneReponse) {
        this.bonneReponse = bonneReponse;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id_question=" + id_question +
                ", contenu='" + contenu + '\'' +
                ", reponse1='" + reponse1 + '\'' +
                ", reponse2='" + reponse2 + '\'' +
                ", reponse3='" + reponse3 + '\'' +
                ", bonneReponse='" + bonneReponse + '\'' +
                '}';
    }
}
