package fr.univamu.iut.paniers;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;

/**
 * Implémentation de l'interface PanierRepositoryInterface,
 * gérant la persistance des paniers dans une base de données MariaDB.
 * Cette classe contient des méthodes pour créer, supprimer et récupérer des paniers.
 */
public class PanierRepositoryMariadb implements PanierRepositoryInterface, AutoCloseable {

    private final Connection dbConnection;

    /**
     * Constructeur qui initialise la connexion à la base de données.
     *
     * @param infoConnection l'URL de connexion à la base de données
     * @param user le nom d'utilisateur pour la connexion
     * @param password le mot de passe pour la connexion
     * @throws SQLException si une erreur se produit lors de la connexion à la base de données
     * @throws ClassNotFoundException si la classe du driver JDBC n'est pas trouvée
     */
    public PanierRepositoryMariadb(String infoConnection, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, password);
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Panier createPanier(Panier panier) {
        String query = "INSERT INTO Panier (name, datdemàj, price, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, panier.getName());
            stmt.setDate(2, new java.sql.Date(panier.getDatdemàj().getTime()));
            stmt.setInt(3, panier.getPrice());
            stmt.setInt(4, panier.getQuantity());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    panier.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return panier;
    }

    @Override
    public Panier getPanier(int id) {
        Panier panier = null;
        String query = "SELECT * FROM Panier WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                String name = result.getString("name");
                Date datdemàj = result.getDate("datdemàj");
                int price = result.getInt("price");
                int quantity = result.getInt("quantity");
                panier = new Panier(name, datdemàj, price, quantity);
                panier.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return panier;
    }

    @Override
    public ArrayList<Panier> getAllPaniers() {
        ArrayList<Panier> paniers = new ArrayList<>();
        String query = "SELECT * FROM Panier";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                String name = result.getString("name");
                Date datdemàj = result.getDate("datdemàj");
                int price = result.getInt("price");
                int quantity = result.getInt("quantity");
                Panier panier = new Panier(name, datdemàj, price, quantity);
                panier.setId(result.getInt("id"));
                paniers.add(panier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paniers;
    }

    @Override
    public boolean updatePanier(int id, String name, Date datdemàj, int price, int quantity) {
        String query = "UPDATE Panier SET name = ?, datdemàj = ?, price = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDate(2, new java.sql.Date(datdemàj.getTime()));
            stmt.setInt(3, price);
            stmt.setInt(4, quantity);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void deletePanier(int id) {
        String query = "DELETE FROM Panier WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getIdPanier(Panier panier) {
        String query = "SELECT id FROM Panier WHERE name = ? AND datdemàj = ? AND price = ? AND quantity = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, panier.getName());
            stmt.setDate(2, new java.sql.Date(panier.getDatdemàj().getTime()));
            stmt.setInt(3, panier.getPrice());
            stmt.setInt(4, panier.getQuantity());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}
