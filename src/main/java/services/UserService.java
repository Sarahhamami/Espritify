package services;

import entities.User;
import utils.DataSources;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserService implements IService<User> {
    private Connection conn;
    private Statement ste;
    public UserService() {
        conn= DataSources.getInstance().getCnx();

    }
    @Override
    public void add(User user) {
        String requete= "insert into utilisateur (nom,prenom,email,mdp,tel,image,rank,score) values " +
                "('"+user.getNom()+"','"+user.getNom()+"', '"+user.getNom()+"', '"+user.getNom()+"', 89999, '"+user.getNom()+"','"+user.getNom()+"', 999 )";
        try {
            ste=conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public User readById(int id) {
        return null;
    }

    @Override
    public List<User> readAllByID(int id) {
        return null;
    }
}
