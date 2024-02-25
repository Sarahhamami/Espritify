package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static Connection cnx;
    private final String url="jdbc:mysql://localhost:3306/test";
    private final String user="root";
    private final String pass="";
    private static dbConnection instance;

    private dbConnection() {
        try{
            cnx= DriverManager.getConnection(url,user,pass);
            System.out.println("conencted");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static dbConnection getInstance(){
        if(instance==null)
            instance=new dbConnection();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
