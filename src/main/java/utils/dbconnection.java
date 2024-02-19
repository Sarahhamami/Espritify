package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {
    private static Connection cnx;
    private final String url="jdbc:mysql://localhost:3306/test";
    private final String user="root";
    private final String pass="";
    private static dbconnection instance;

    private dbconnection() {
        try{
            cnx= DriverManager.getConnection(url,user,pass);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static dbconnection getInstance(){
        if(instance==null)
            instance=new dbconnection();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
