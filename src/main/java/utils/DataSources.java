package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSources {
    private  Connection cnx;
    private  String url="jdbc:mysql://localhost:3306/espritify";
    private  String login="root";
    private   String pwd="";

    private static DataSources instance;

    public DataSources() {
        try {
            cnx= DriverManager.getConnection(url,login,pwd);
            System.out.println("succes");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static DataSources getInstance(){
        if(instance==null)
            instance=new DataSources();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
