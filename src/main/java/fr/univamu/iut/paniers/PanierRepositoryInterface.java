package fr.univamu.iut.paniers;

//import fr.univamu.iut.Panier_Produit.Panier_Produit;

import java.util.*;


public interface PanierRepositoryInterface {
    public void close();
    public Panier createPanier(Panier panier);
    public Panier getPanier(int id);
    public List<Panier> getPaniers();
    public void deletePanier(int id);
    public boolean updatePanier(int id, String name, Date datdemàj, int price, int quantity);
    public int getIdPanier(Panier panier);

    ArrayList<Panier> getAllPaniers();
//    public void addProduitPanier(Panier_Produit panier_produit);
//    public void deleteProduitPanier(Panier_Produit panier_produit);
//    public void updateProduitPanier(Panier_Produit panier_produit);
//    public List<Panier_Produit> getPaniersProduit();
//    public Panier_Produit getPaniersProduit(int id_produit);

}
