package fr.univamu.iut.commande;

import java.util.ArrayList;

public interface CommandeRepositoryInterface {
    /**
     *  Méthode fermant le dépôt où sont stockées les informations sur les commandes
     */
    public void close();

    /**
     * Méthode créant la commande dont l'identifiant est passée en paramètre
     * @param prix prix de la commande
     * @param numPanier numéro du panier de la commande
     * @param loc localisation du relai
     * @param date date de retrait de la commande
     * @return true si la commande a été enregistré, false sinon
     */
    public boolean registerCommande(int numPanier, int prix, String loc, String date );

    /**
     * Méthode retournant la commande dont l'identifiant est passée en paramètre
     * @param id identifiant de la commande
     * @return un objet Commande représentant la commande recherchée
     */
    public Commande getCommande( int id );

    /**
     * Méthode mettant à jour la commande
     * @param prix prix de la commande
     * @param numPanier numéro du panier de la commande
     * @param loc localisation du relai
     * @param date date de retrait de la commande
     */
    public void updateCommande( int numPanier, int prix, String loc, String date );

    /**
     * Méthode supprimant la commande
     * @param id identifiant de la commande
     */
    public void deleteCommande( int id );

    /**
     * Méthode retournant la liste des Commandes
     * @return une liste d'objets Commande
     */
    public ArrayList<Commande> getAllCommandes() ;
}
