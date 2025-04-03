package fr.univamu.iut.paniers;

//import fr.univamu.iut.paniers.Panier_Produit.Panier_Produit;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PanierRepositoryMariadb implements PanierRepositoryInterface, Closeable {

    private final Connection dbConnection;

    public PanierRepositoryMariadb(String infoConnection, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, password);
    }


    @Override
    public void close(){
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Panier createPanier(Panier panier) {
        String query = "INSERT INTO Panier (name, datdemàj, price, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, panier.getName());
            stmt.setDate(2, new java.sql.Date(panier.getDatdemàj().getTime()));
            stmt.setInt(3, panier.getPrice());
            stmt.setInt(4, panier.getQuantity());
            stmt.executeUpdate();

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

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return panier;
    }

    @Override
    public List<Panier> getPaniers() {
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
                paniers.add(panier);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return paniers;
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
    public boolean updatePanier(int id, String name, Date datdemàj, int price, int quantity) {
        String query = "UPDATE Panier SET name = ?, datdemàj = ?, price = ?, quantity = ? WHERE id = ?";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDate(2, new java.sql.Date(datdemàj.getTime()));
            stmt.setInt(3, price);
            stmt.setInt(4, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public int getIdPanier(Panier panier) {
        String query = "SELECT id FROM Panier WHERE name = ? AND datdemàj = ? AND price = ? AND quantity = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, panier.getName());
            stmt.setDate(2, new java.sql.Date(panier.getDatdemàj().getTime()));
            stmt.setInt(3, panier.getPrice());
            stmt.setInt(4, panier.getQuantity());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public ArrayList<Panier> getAllPaniers() {
        return null;
    }
//
//    @Override
//    public void addProduitPanier(Panier_Produit panier_produit) {
//        String query = "INSERT INTO Panier_Produit (id_panier, id_produit, quantity_produit) VALUES (?, ?, ?)";
//        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
//            stmt.setInt(1, panier_produit.getId_panier());
//            stmt.setInt(2, panier_produit.getId_produit());
//            stmt.setInt(3, panier_produit.getQuantite());
//            stmt.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void deleteProduitPanier(Panier_Produit panier_produit) {
//        String query = "DELETE FROM Panier_Produit WHERE id_panier = ? AND id_produit = ?";
//        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
//            stmt.setInt(1, panier_produit.getId_panier());
//            stmt.setInt(2, panier_produit.getId_produit());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void updateProduitPanier(Panier_Produit panier_produit) {
//        String query = "UPDATE Panier_Produit SET quantity_produit = ? WHERE id_panier = ? AND id_produit = ?";
//
//        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
//            stmt.setInt(1, panier_produit.getQuantite());
//            stmt.setInt(2, panier_produit.getId_panier());
//            stmt.setInt(3, panier_produit.getId_produit());
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public List<Panier_Produit> getPaniersProduit() {
//        ArrayList<Panier_Produit> paniers = new ArrayList<>();
//        String query = "SELECT * FROM Panier_Produit";
//
//        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
//            ResultSet result = stmt.executeQuery();
//            while (result.next()) {
//                int id_panier = result.getInt("id_panier");
//                int id_produit = result.getInt("id_produit");
//                int quantity_produit = result.getInt("quantity_produit");
//                Panier_Produit panier_produit = new Panier_Produit(id_panier, id_produit, quantity_produit);
//                paniers.add(panier_produit);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return paniers;
//    }
//
//    @Override
//    public Panier_Produit getPaniersProduit(int id_produit) {
//        Panier_Produit panier_produit = null;
//        String query = "SELECT * FROM Panier_Produit WHERE id_produit = ?";
//
//        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
//            stmt.setInt(1, id_produit);
//            ResultSet result = stmt.executeQuery();
//            if (result.next()) {
//                int id_panier = result.getInt("id_panier");
//                int quantity_produit = result.getInt("quantity_produit");
//                panier_produit = new Panier_Produit(id_panier, id_produit, quantity_produit);
//
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return panier_produit;
//    }
}