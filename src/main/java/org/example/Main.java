package org.example;

import entities.User;
import services.UserService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        User u= new User("sarah");
        UserService us= new UserService();
        us.add(u);
    }
}