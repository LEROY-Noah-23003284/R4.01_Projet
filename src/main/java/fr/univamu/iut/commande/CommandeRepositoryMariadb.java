package fr.univamu.iut.commande;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;

public class CommandeRepositoryMariadb implements CommandeRepositoryInterface{
    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;

    /**
     * Constructeur de la classe
     * @param infoConnection chaîne de caractères avec les informations de connexion
     * @param user chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd chaîne de caractères contenant le mot de passe à utiliser
     */
    public CommandeRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public boolean registerCommande(int numPanier, int prix, String loc, String date) {
        int nbRowInserted = 0;

        String query = "INSERT INTO Commande (numPanier, prix, loc, date) VALUES  (?, ?, ?, ?)";
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1,numPanier);
            ps.setInt(2,prix);
            ps.setString(3,loc);
            ps.setDate(4, Date.valueOf(date));

            nbRowInserted = ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (nbRowInserted != 0);
    }

    @Override
    public Commande getCommande(int id) {
        Commande selectedCommande = null;

        String query = "SELECT * FROM Commande WHERE id = ?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, id);

            // exécution de la requête
            ResultSet result = ps.executeQuery();


            // S'il y a une commande
            if ( result.next() )
            {
                int numPanier = result.getInt("numPanier");
                int prix = result.getInt("prix");
                String loc = result.getString("loc");
                String date = result.getString("date");

                // création de la commande courante
                selectedCommande = new Commande(id, numPanier, prix, loc, date);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedCommande;
    }

    @Override
    public void updateCommande(int numPanier, int prix, String loc, String date) {

    }

    @Override
    public void deleteCommande(int id) {

    }

    @Override
    public ArrayList<Commande> getAllCommandes() {
        ArrayList<Commande> listCommandes ;

        String query = "SELECT * FROM Commande";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listCommandes = new ArrayList<>();

            while ( result.next() )
            {
                int id = result.getInt("id");
                int numPanier = result.getInt("numPanier");
                int prix = result.getInt("prix");
                String loc = result.getString("loc");
                String date = result.getString("date");

                // création de la commande courante
                Commande currentCommande = new Commande(id, numPanier, prix, loc, date);

                // ajout de la commande dans la liste
                listCommandes.add(currentCommande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCommandes;

    }
}
