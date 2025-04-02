package fr.univamu.iut.prodanduser;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accéder aux utilisateurs stockés dans une base de données MariaDB.
 * Implémente l'interface {@link UserRepositoryInterface} et l'interface {@link Closeable}.
 */
public class UserRepositoryMariadb implements UserRepositoryInterface, Closeable{
    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;

    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Constructeur de la classe
     * @param infoConnection chaîne de caractères avec les informations de connexion
     * @param user chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd chaîne de caractères contenant le mot de passe à utiliser
     */
    public UserRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }
    @Override
    public User getUser(String mail) {

        User selectedUser = null;

        String query = "SELECT * FROM User WHERE mail=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, mail);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si la référence du livre est valide)
            if( result.next() )
            {
                String name = result.getString("name");
                String pwd = result.getString("pwd");
                int admin = result.getInt("admin");

                // création et initialisation de l'objet User
                selectedUser = new User(mail, name, admin, pwd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUser;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> listUsers ;

        String query = "SELECT * FROM User";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listUsers = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                int admin = result.getInt("admin");
                String name = result.getString("name");
                String pwd = result.getString("pwd");
                String mail = result.getString("mail");

                // création du livre courant
                User currentUser = new User(mail, name, admin, pwd);

                listUsers.add(currentUser);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL: " + e.getMessage()); // Ajoutez ceci
            throw new RuntimeException(e);
        }
        return listUsers;
    }

    @Override
    public ArrayList<User> getAllUsersAdmin() {
        ArrayList<User> listUsers ;

        String query = "SELECT * FROM User WHERE admin=1";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listUsers = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                String name = result.getString("name");
                String pwd = result.getString("pwd");
                String mail = result.getString("mail");

                // création du livre courant
                User currentUser = new User(mail, name, 1, pwd);

                listUsers.add(currentUser);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL: " + e.getMessage()); // Ajoutez ceci
            throw new RuntimeException(e);
        }
        return listUsers;
    }

    @Override
    public ArrayList<User> getAllUsersNonAdmin() {
        ArrayList<User> listUsers ;

        String query = "SELECT * FROM User WHERE admin=0";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listUsers = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                String name = result.getString("name");
                String pwd = result.getString("pwd");
                String mail = result.getString("mail");

                // création du livre courant
                User currentUser = new User(mail, name, 0, pwd);

                listUsers.add(currentUser);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL: " + e.getMessage()); // Ajoutez ceci
            throw new RuntimeException(e);
        }
        return listUsers;
    }

}
