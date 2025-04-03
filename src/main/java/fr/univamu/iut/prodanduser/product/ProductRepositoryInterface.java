package fr.univamu.iut.prodanduser.product;

import java.util.ArrayList;

/**
 * Interface définissant les opérations possibles sur un dépôt d'utilisateurs.
 */
public interface ProductRepositoryInterface {

    /**
     * Ferme la connexion au dépôt des utilisateurs.
     */
    public void close();

    /**
     * Recherche un utilisateur par son adresse email.
     *
     * @param id Adresse email de l'utilisateur (clé primaire).
     * @return Un objet {@link Product} correspondant à l'email fourni, ou null si l'utilisateur n'existe pas.
     */
    public Product getProduct(int id);

    /**
     * Récupère la liste de tous les utilisateurs.
     *
     * @return Une liste contenant tous les utilisateurs.
     */
    public ArrayList<Product> getAllProducts();

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     *
     * @param product L'objet {@link Product} représentant l'utilisateur à ajouter.
     */
    public void createProduct(Product product);

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param product L'objet {@link Product} contenant les nouvelles informations.
     */
    public void updateProduct(Product product);

    /**
     * Supprime un utilisateur de la base de données en fonction de son adresse email.
     *
     * @param id L'adresse email de l'utilisateur à supprimer.
     */
    public void deleteProduct(int id);
}
