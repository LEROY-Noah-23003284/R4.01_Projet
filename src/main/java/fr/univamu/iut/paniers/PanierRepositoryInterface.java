package fr.univamu.iut.paniers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface définissant les méthodes pour gérer les paniers dans la base de données.
 * Permet de définir les opérations CRUD : création, récupération, mise à jour et suppression des paniers.
 */
public interface PanierRepositoryInterface {

    /**
     * Récupère un panier par son ID.
     *
     * @param id l'ID du panier à récupérer
     * @return le panier correspondant à l'ID
     */
    Panier getPanier(int id);

    /**
     * Récupère tous les paniers.
     *
     * @return une liste de tous les paniers
     */
    ArrayList<Panier> getAllPaniers();

    /**
     * Met à jour les informations d'un panier.
     *
     * @param id l'ID du panier à mettre à jour
     * @param name le nouveau nom du panier
     * @param datdemàj la nouvelle date de demande
     * @param price le nouveau prix du panier
     * @param quantity la nouvelle quantité de produits
     * @return true si la mise à jour a réussi, false sinon
     */
    boolean updatePanier(int id, String name, Date datdemàj, int price, int quantity);

    /**
     * Crée un nouveau panier.
     *
     * @param panier le panier à créer
     * @return le panier créé
     */
    Panier createPanier(Panier panier);

    /**
     * Supprime un panier par son ID.
     *
     * @param id l'ID du panier à supprimer
     */
    void deletePanier(int id);

    /**
     * Récupère l'ID d'un panier à partir de l'objet panier.
     *
     * @param panier l'objet panier dont l'ID doit être récupéré
     * @return l'ID du panier
     */
    int getIdPanier(Panier panier);


}
