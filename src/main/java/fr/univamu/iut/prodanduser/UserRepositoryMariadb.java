package fr.univamu.iut.prodanduser;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accéder aux utilisateurs stockés dans une base de données MariaDB.
 * Implémente l'interface {@link UserRepositoryInterface} et l'interface {@link Closeable}.
 */
public class UserRepositoryMariadb implements UserRepositoryInterface, Closeable {
    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;

    /**
     * Ferme la connexion à la base de données.
     */
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
     * Constructeur de la classe.
     * @param infoConnection Chaîne contenant les informations de connexion à la base de données.
     * @param user Identifiant de connexion à la base de données.
     * @param pwd Mot de passe de connexion à la base de données.
     * @throws SQLException Si une erreur SQL survient.
     * @throws ClassNotFoundException Si le driver JDBC n'est pas trouvé.
     */
    public UserRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    /**
     * Recherche un utilisateur par son email.
     * @param mail Email de l'utilisateur.
     * @return Un objet {@link User} correspondant ou null si l'utilisateur n'existe pas.
     */
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

    /**
     * Récupère la liste de tous les utilisateurs.
     * @return Une liste contenant tous les utilisateurs.
     */
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

    /**
     * Récupère la liste des utilisateurs administrateurs.
     * @return Une liste d'utilisateurs administrateurs.
     */
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

    /**
     * Récupère la liste des utilisateurs non administrateurs.
     * @return Une liste d'utilisateurs non administrateurs.
     */
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

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     * @param user Utilisateur à ajouter.
     */
    @Override
    public void createUser(User user) {
        String query = "INSERT INTO User (mail, name, pwd, admin) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, user.getMail());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPwd());
            ps.setInt(4, user.getAdmin());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de l'utilisateur: " + e.getMessage(), e);
        }
    }

    /**
     * Met à jour un utilisateur existant.
     * @param user Utilisateur avec les nouvelles informations.
     */
    @Override
    public void updateUser(User user) {
        String query = "UPDATE User SET name=?, pwd=?, admin=? WHERE mail=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPwd());
            ps.setInt(3, user.getAdmin());
            ps.setString(4, user.getMail());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'utilisateur: " + e.getMessage(), e);
        }
    }

    /**
     * Supprime un utilisateur en fonction de son adresse email.
     * @param mail Email de l'utilisateur à supprimer.
     */
    @Override
    public void deleteUser(String mail) {
        String query = "DELETE FROM User WHERE mail=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, mail);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'utilisateur: " + e.getMessage(), e);
        }
    }


}