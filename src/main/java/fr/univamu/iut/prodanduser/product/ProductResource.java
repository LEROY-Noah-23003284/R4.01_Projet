package fr.univamu.iut.prodanduser.product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource RESTful pour gérer les utilisateurs.
 * Fournit des endpoints pour récupérer, ajouter, mettre à jour et supprimer des utilisateurs.
 */
@Path("/product")
@ApplicationScoped
public class ProductResource {
    /**
     * Service utilisé pour accéder aux données des utilisateurs et les manipuler.
     */
    @Inject
    private ProductService service;

    /**
     * Endpoint permettant de récupérer tous les utilisateurs enregistrés au format JSON.
     * @return Une chaîne JSON représentant la liste des utilisateurs.
     */
    @GET
    @Produces("application/json")
    public String getAllProducts() {
        return service.getAllProductsJSON();
    }

    /**
     * Endpoint permettant de récupérer un utilisateur spécifique selon son email.
     * @param id L'email de l'utilisateur recherché.
     * @return Une chaîne JSON contenant les informations de l'utilisateur.
     * @throws NotFoundException Si l'utilisateur n'est pas trouvé.
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
     * Endpoint permettant de mettre à jour un utilisateur existant.
     * @param product L'objet utilisateur contenant les nouvelles informations.
     * @return Une réponse HTTP confirmant la mise à jour.
     */
    @PUT
    @Path("/update")
    @Consumes("application/json")
    public Response updateProduct(Product product) {
        service.updateProduct(product);
        return Response.ok("updated").build();
    }

    /**
     * Endpoint permettant de supprimer un utilisateur existant.
     * @param product L'objet utilisateur contenant l'email de l'utilisateur à supprimer.
     * @return Une réponse HTTP confirmant la suppression.
     */
    @PUT
    @Path("/delete")
    @Consumes("application/json")
    public Response deleteProduct(Product product) {
        service.deleteProduct(product.getId());
        return Response.ok("deleted").build();
    }

    /**
     * Endpoint permettant d'ajouter un nouvel utilisateur.
     * @param product L'objet utilisateur à ajouter.
     * @return Une réponse HTTP confirmant l'ajout.
     */
    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addProduct(Product product) {
        service.createProduct(product);
        return Response.ok("add").build();
    }
}
