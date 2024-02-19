package entites;

public class Quizz {
    private int id;
    private String question;

    public Quizz(int id, String question) {
        this.id=id;
        this.question=question;
    }

    public Quizz(String question){
        this.question=question;
    }

    public Quizz() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Quizz{" + "id=" + id + ", question='" + question + '\'' + '}';
    }


}
