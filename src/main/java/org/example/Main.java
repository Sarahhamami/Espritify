package org.example;
import entities.Message;
import entities.Conversation;
import entities.Reclamation;
import entities.User;
import services.ConversationService;
import services.MessageService;
import services.*;
import entities.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        //Reclamation r1=new Reclamation(88,"ahmed","description");
        Conversation c1=new Conversation(56,"jai une question","matrice gauss seidel");
        ConversationService cs1=new ConversationService();
        ReclamationService rs1=new ReclamationService();
        //Reclamation r2=new Reclamation(91,"conception","uml");
        MessageService ms1= new MessageService();
        Conversation c2=new Conversation(17,56,"j ai une question","concernant les matrices");
        Message msg=new Message(56,11,"je suis");
        //System.out.println(cs1.readAll());
        System.out.println(ms1.readById(7));
        ReponseRecService rrs1=new ReponseRecService();
        Reponse_rec rr=new Reponse_rec(75,88,"god","of war","");
        //System.out.println(rs1.readAll());
        Reclamation rss=new Reclamation(91);
        System.out.println(ms1.readAll());
    }
}