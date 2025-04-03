package fr.univamu.iut.prodanduser.product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

/**
 * Service gérant les opérations liées aux utilisateurs.
 * Il permet la récupération, la création, la mise à jour et la suppression des utilisateurs.
 * Les données sont stockées dans un dépôt implémentant {@link ProductRepositoryInterface}.
 */
@ApplicationScoped
public class ProductService {

    /**
     * Dépôt utilisé pour accéder aux données des utilisateurs.
     */
    protected ProductRepositoryInterface productRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données.
     *
     * @param productRepo objet implémentant l'interface d'accès aux données.
     */
    @Inject
    public ProductService(ProductRepositoryInterface productRepo) {
        this.productRepo = productRepo;
    }

    /**
     * Constructeur par défaut.
     */
    public ProductService() {}

    /**
     * Récupère tous les utilisateurs sous forme de JSON.
     *
     * @return Une chaîne de caractères contenant les utilisateurs au format JSON.
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
     * Récupère un utilisateur spécifique sous forme de JSON.
     *
     * @param id L'adresse e-mail de l'utilisateur recherché.
     * @return Une chaîne de caractères contenant les informations de l'utilisateur au format JSON,
     * ou {@code null} si l'utilisateur n'existe pas.
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
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param product L'utilisateur avec les nouvelles informations.
     */
    public void updateProduct(Product product) {
        productRepo.updateProduct(product);
    }

    /**
     * Supprime un utilisateur en fonction de son adresse e-mail.
     *
     * @param id L'adresse e-mail de l'utilisateur à supprimer.
     */
    public void deleteProduct(int id) {
        productRepo.deleteProduct(id);
    }

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     *
     * @param product L'utilisateur à ajouter.
     */
    public void createProduct(Product product) {
        productRepo.createProduct(product);
    }
}