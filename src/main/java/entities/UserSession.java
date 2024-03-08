package entities;

import java.util.HashSet;

public final class UserSession {

    private static UserSession instance;

    public static String userName;
    private static String privileges;

    private UserSession(String userName, String privileges) {
        this.userName = userName;
        this.privileges = privileges;
    }

    public static UserSession getInstace(String userName, String privileges) {
        if(instance == null) {
            instance = new UserSession(userName, privileges);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public String getPrivileges() {
        return privileges;
    }

    public static void cleanUserSession() {
        userName = "";// or null
        privileges = String.valueOf(new HashSet<>());// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", privileges=" + privileges +
                '}';
    }
}