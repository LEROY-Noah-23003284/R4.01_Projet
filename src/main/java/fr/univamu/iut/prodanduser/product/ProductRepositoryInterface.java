package fr.univamu.iut.prodanduser.product;

import java.util.ArrayList;

/**
 * Interface définissant les opérations possibles sur un dépôt de produits.
 * Cette interface permet de gérer l'accès aux données des produits, y compris leur ajout, mise à jour, suppression et récupération.
 */
public interface ProductRepositoryInterface {

    /**
     * Ferme la connexion au dépôt des produits.
     * Cette méthode libère toutes les ressources utilisées par le dépôt de données.
     */
    public void close();

    /**
     * Recherche un produit par son identifiant.
     *
     * @param id Identifiant unique du produit (clé primaire).
     * @return Un objet {@link Product} correspondant à l'identifiant fourni, ou null si le produit n'existe pas.
     */
    public Product getProduct(int id);

    /**
     * Récupère la liste de tous les produits.
     *
     * @return Une liste contenant tous les produits disponibles dans le dépôt.
     */
    public ArrayList<Product> getAllProducts();

    /**
     * Ajoute un nouveau produit à la base de données.
     *
     * @param product L'objet {@link Product} représentant le produit à ajouter.
     */
    public void createProduct(Product product);

    /**
     * Met à jour les informations d'un produit existant.
     *
     * @param product L'objet {@link Product} contenant les nouvelles informations du produit.
     */
    public void updateProduct(Product product);

    /**
     * Supprime un produit de la base de données en fonction de son identifiant.
     *
     * @param id L'identifiant du produit à supprimer.
     */
    public void deleteProduct(int id);
}