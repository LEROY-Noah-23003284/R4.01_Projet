package fr.univamu.iut.prodanduser.product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource RESTful pour gérer les produits.
 * Fournit des endpoints pour récupérer, ajouter, mettre à jour et supprimer des produits.
 * Cette classe utilise l'annotation Jakarta EE pour la gestion des services et des réponses HTTP.
 */
@Path("/product")
@ApplicationScoped
public class ProductResource {

    /**
     * Service utilisé pour accéder aux données des produits et les manipuler.
     * Cette instance est injectée par le conteneur de dépendances de Jakarta.
     */
    @Inject
    private ProductService service;

    /**
     * Endpoint permettant de récupérer tous les produits enregistrés au format JSON.
     * Ce endpoint envoie une réponse contenant la liste des produits au format JSON.
     *
     * @return Une chaîne JSON représentant la liste des produits.
     */
    @GET
    @Produces("application/json")
    public String getAllProducts() {
        return service.getAllProductsJSON();
    }

    /**
     * Endpoint permettant de récupérer un produit spécifique selon son identifiant.
     * Ce endpoint envoie une réponse contenant les informations du produit au format JSON.
     *
     * @param id Identifiant du produit recherché.
     * @return Une chaîne JSON contenant les informations du produit.
     * @throws NotFoundException Si le produit n'est pas trouvé dans la base de données.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getProduct(@PathParam("id") int id) {
        String result = service.getProductJSON(id);
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    /**
     * Endpoint permettant de mettre à jour un produit existant.
     * Ce endpoint reçoit un produit modifié et met à jour ses informations dans la base de données.
     *
     * @param product L'objet produit contenant les nouvelles informations.
     * @return Une réponse HTTP confirmant la mise à jour du produit.
     */
    @PUT
    @Path("/update")
    @Consumes("application/json")
    public Response updateProduct(Product product) {
        service.updateProduct(product);
        return Response.ok("updated").build();
    }

    /**
     * Endpoint permettant de supprimer un produit existant.
     * Ce endpoint reçoit un produit contenant l'identifiant de celui à supprimer.
     *
     * @param product L'objet produit contenant l'identifiant du produit à supprimer.
     * @return Une réponse HTTP confirmant la suppression du produit.
     */
    @PUT
    @Path("/delete")
    @Consumes("application/json")
    public Response deleteProduct(Product product) {
        service.deleteProduct(product.getId());
        return Response.ok("deleted").build();
    }

    /**
     * Endpoint permettant d'ajouter un nouveau produit.
     * Ce endpoint reçoit un produit et l'ajoute à la base de données.
     *
     * @param product L'objet produit à ajouter à la base de données.
     * @return Une réponse HTTP confirmant l'ajout du produit.
     */
    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addProduct(Product product) {
        service.createProduct(product);
        return Response.ok("add").build();
    }
}