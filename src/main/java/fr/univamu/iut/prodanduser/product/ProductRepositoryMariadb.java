package fr.univamu.iut.prodanduser.product;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accéder aux utilisateurs stockés dans une base de données MariaDB.
 * Implémente l'interface {@link ProductRepositoryInterface} et l'interface {@link Closeable}.
 */
public class ProductRepositoryMariadb implements ProductRepositoryInterface, Closeable{
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
    public ProductRepositoryMariadb(String infoConnection, String user, String pwd ) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    /**
     * Recherche un utilisateur par son email.
     * @param id Email de l'utilisateur.
     * @return Un objet {@link Product} correspondant ou null si l'utilisateur n'existe pas.
     */
    @Override
    public Product getProduct(int id) {

        Product selectedProduct = null;

        String query = "SELECT * FROM Product WHERE id=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, id);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si la référence du livre est valide)
            if( result.next() )
            {
                String name = result.getString("name");
                String type = result.getString("type");
                String season = result.getString("season");
                float price = result.getFloat("price");

                // création et initialisation de l'objet Product
                selectedProduct = new Product(id, name, type, season, price);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedProduct;
    }

    /**
     * Récupère la liste de tous les utilisateurs.
     * @return Une liste contenant tous les utilisateurs.
     */
    @Override
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> listProducts;

        String query = "SELECT * FROM Product";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listProducts = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                int id = result.getInt("id");
                String name = result.getString("name");
                String type = result.getString("type");
                String season = result.getString("season");
                float price = result.getFloat("price");

                // création du livre courant
                Product currentProduct = new Product(id, name, type, season, price);

                listProducts.add(currentProduct);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL: " + e.getMessage()); // Ajoutez ceci
            throw new RuntimeException(e);
        }
        return listProducts;
    }

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     * @param product Utilisateur à ajouter.
     */
    @Override
    public void createProduct(Product product) {
        String query = "INSERT INTO Product (id, name, type, season, price) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, product.getId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getType());
            ps.setString(4, product.getSeason());
            ps.setFloat(5, product.getPrice());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de l'utilisateur: " + e.getMessage(), e);
        }
    }

    /**
     * Met à jour un utilisateur existant.
     * @param product Utilisateur avec les nouvelles informations.
     */
    @Override
    public void updateProduct(Product product) {
        String query = "UPDATE Product SET name=?, type=?, season=?, price=? WHERE id=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(2, product.getName());
            ps.setString(3, product.getType());
            ps.setString(4, product.getSeason());
            ps.setFloat(5, product.getPrice());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'utilisateur: " + e.getMessage(), e);
        }
    }

    /**
     * Supprime un utilisateur en fonction de son adresse email.
     * @param id Email de l'utilisateur à supprimer.
     */
    @Override
    public void deleteProduct(int id) {
        String query = "DELETE FROM Product WHERE id=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'utilisateur: " + e.getMessage(), e);
        }
    }


}
