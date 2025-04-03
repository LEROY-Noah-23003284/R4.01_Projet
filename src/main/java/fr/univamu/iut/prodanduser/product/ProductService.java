package fr.univamu.iut.prodanduser.product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

/**
 * Service gérant les opérations liées aux produits.
 * Il permet la récupération, la création, la mise à jour et la suppression des produits.
 * Les données sont stockées dans un dépôt implémentant {@link ProductRepositoryInterface}.
 * Ce service transforme les objets {@link Product} en chaînes JSON et inversement pour faciliter l'échange de données.
 */
@ApplicationScoped
public class ProductService {

    /**
     * Dépôt utilisé pour accéder aux données des produits.
     * Cet objet implémente l'interface {@link ProductRepositoryInterface}, permettant l'accès aux produits.
     */
    protected ProductRepositoryInterface productRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données via l'interface {@link ProductRepositoryInterface}.
     * Ce constructeur est utilisé pour l'injection de dépendances par Jakarta.
     *
     * @param productRepo objet implémentant l'interface d'accès aux données des produits.
     */
    @Inject
    public ProductService(ProductRepositoryInterface productRepo) {
        this.productRepo = productRepo;
    }

    /**
     * Constructeur par défaut.
     * Utilisé principalement pour la création d'instances dans des contextes où l'injection de dépendances n'est pas utilisée.
     */
    public ProductService() {}

    /**
     * Récupère tous les produits sous forme de chaîne JSON.
     *
     * @return Une chaîne de caractères contenant la liste de tous les produits au format JSON.
     *         Si une erreur survient lors de la conversion en JSON, la chaîne retournée sera {@code null}.
     */
    public String getAllProductsJSON() {
        ArrayList<Product> allProducts = productRepo.getAllProducts();
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allProducts);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Récupère un produit spécifique sous forme de chaîne JSON.
     *
     * @param id L'identifiant (par exemple, l'email) du produit recherché.
     * @return Une chaîne de caractères contenant les informations du produit au format JSON,
     *         ou {@code null} si le produit n'existe pas.
     */
    public String getProductJSON(int id) {
        String result = null;
        Product myProduct = productRepo.getProduct(id);
        if (myProduct != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myProduct);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Met à jour les informations d'un produit existant dans le dépôt.
     *
     * @param product L'objet {@link Product} contenant les nouvelles informations à mettre à jour.
     */
    public void updateProduct(Product product) {
        productRepo.updateProduct(product);
    }

    /**
     * Supprime un produit en fonction de son identifiant (par exemple, l'email).
     *
     * @param id L'identifiant du produit à supprimer.
     */
    public void deleteProduct(int id) {
        productRepo.deleteProduct(id);
    }

    /**
     * Ajoute un nouveau produit dans le dépôt.
     *
     * @param product L'objet {@link Product} représentant le produit à ajouter.
     */
    public void createProduct(Product product) {
        productRepo.createProduct(product);
    }
}