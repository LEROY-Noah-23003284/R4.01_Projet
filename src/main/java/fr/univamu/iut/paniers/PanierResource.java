package fr.univamu.iut.paniers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Ressource REST pour gérer les opérations CRUD sur les paniers.
 * Cette classe expose les endpoints pour créer, récupérer, mettre à jour et supprimer des paniers.
 * Les opérations sont effectuées via des méthodes HTTP appropriées (GET, POST, PUT, DELETE).
 */
@Path("/paniers")
@ApplicationScoped
public class PanierResource {

    private PanierService service;


    public PanierResource() {}

    /**
     * Constructeur pour injecter le service de gestion des paniers.
     * Le service gère la logique métier associée aux paniers.
     *
     * @param panierRepo le repository des paniers à utiliser dans le service
     */
    @Inject
    public PanierResource(PanierRepositoryInterface panierRepo) {
        this.service = new PanierService(panierRepo);
    }

    /**
     * Récupère tous les paniers sous forme de JSON.
     * Cette méthode appelle le service pour obtenir la liste de tous les paniers disponibles
     * et les renvoie dans la réponse HTTP au format JSON.
     *
     * @return la liste des paniers sous forme JSON
     */
    @GET
    @Produces("application/json")
    public String getAllPaniers() {
        return service.getAllPaniersJSON();
    }

    /**
     * Récupère un panier spécifique par son ID.
     * Si le panier est trouvé, il est retourné au format JSON.
     * Si le panier n'est pas trouvé, une erreur HTTP 404 (Not Found) est retournée.
     *
     * @param id l'ID du panier à récupérer
     * @return la réponse HTTP contenant le panier en format JSON
     * @throws NotFoundException si le panier avec l'ID spécifié n'existe pas
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getPanier(@PathParam("id") int id) {
        String result = service.getPanierJSON(id);
        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Panier avec ID " + id + " non trouvé.")
                    .build();
        }
        return Response.ok(result).build();
    }

    /**
     * Crée un panier avec les données envoyées dans la requête HTTP.
     * Les informations du panier sont envoyées en JSON dans le corps de la requête.
     * Si la création est réussie, une réponse HTTP 201 (Created) est retournée avec le panier créé.
     *
     * @param panier le panier à créer, avec les informations envoyées dans la requête
     * @return la réponse HTTP avec l'ID du panier créé
     */
    @POST
    @Consumes("application/json")
    public Response createPanier(Panier panier) {
        Panier createdPanier = service.createPanier(panier);
        return Response.status(Response.Status.CREATED).entity(createdPanier).build();
    }

    /**
     * Met à jour un panier existant avec les données envoyées dans la requête HTTP.
     * L'ID du panier est spécifié dans l'URL de la requête.
     * Si le panier est mis à jour avec succès, une réponse HTTP 200 (OK) est retournée.
     * Si le panier n'est pas trouvé, une erreur HTTP 404 (Not Found) est retournée.
     *
     * @param id l'ID du panier à mettre à jour
     * @param panier les nouvelles données du panier envoyées dans la requête
     * @return une réponse HTTP indiquant que le panier a été mis à jour
     * @throws NotFoundException si le panier avec l'ID spécifié n'existe pas
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updatePanier(@PathParam("id") int id, Panier panier) {
        if (!service.updatePanier(id, panier)) {
            throw new NotFoundException();
        }
        return Response.ok("Panier mis à jour avec succès").build();
    }

    /**
     * Supprime un panier spécifique par son ID.
     * Si le panier est supprimé avec succès, une réponse HTTP 204 (No Content) est retournée.
     * Si le panier n'est pas trouvé, une erreur HTTP 404 (Not Found) est retournée.
     *
     * @param id l'ID du panier à supprimer
     * @return une réponse HTTP indiquant que le panier a été supprimé
     * @throws NotFoundException si le panier avec l'ID spécifié n'existe pas
     */
    @DELETE
    @Path("{id}")
    public Response deletePanier(@PathParam("id") int id) {
        service.deletePanier(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
