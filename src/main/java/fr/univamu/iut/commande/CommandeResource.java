package fr.univamu.iut.commande;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


/**
 * Ressource associée aux commandes
 * (point d'accès de l'API REST)
 */
@Path("/commandes")
@ApplicationScoped
public class CommandeResource {
    /**
     * Service utilisé pour accéder aux données de commandes et récupérer/modifier/supprimer leurs informations
     */
    private CommandeService service;

    /**
     * Constructeur par défaut
     */
    public CommandeResource(){}

    /**
     * Constructeur permettant d'initialiser le service avec les interfaces d'accès aux données
     * @param reservationRepo objet implémentant l'interface d'accès aux données des commandes
     */
    public @Inject CommandeResource(CommandeRepositoryInterface reservationRepo){
        this.service = new CommandeService( reservationRepo ) ;
    }

    /**
     * Enpoint permettant de publier de toutes les commandes enregistrées
     * @return la liste des commandes (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllCommandes() {
        return service.getAllCommandesJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'une commande
     * dont l'identifiant est passée paramètre dans le chemin
     * @param id identifiant de la commande recherché
     * @return les informations de la commande recherché au format JSON
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getCommande( @PathParam("id") int id){

        String result = service.getCommandeJSON( id );

        // si aucune commande n'a été trouvée
        // on retourne simplement un JSON vide
        if( result == null )
            return "{}";

        return result;
    }

    /**
     * Enpoint permettant de soumettre une nouvelle commande d'un panier
     * @param numPanier numéro du panier
     * @param prix prix de la commande
     * @param loc localisation du relai
     * @param date date de retrait de la commande
     * @return un objet Response indiquant "registred" si la commande a été enregistrée ou une erreur "conflict" sinon
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response registerCommande(@FormParam("numPanier") int numPanier, @FormParam("prix") int prix, @FormParam("loc") String loc, @FormParam("date") String date){

        if( service.registerCommande(numPanier, prix, loc, date) )
            return Response.ok("registred").build();
        else
            return Response.status( Response.Status.CONFLICT ).build();
    }

    /**
     * Endpoint permettant de mettre à jour une commande
     * @param id identifiant de la commande
     * @param numPanier numéro du panier
     * @param prix prix de la commande
     * @param loc localisation du relai
     * @param date date de retrait de la commande
     * @return un objet Response indiquant "registred" si la commande a été mise à jour ou une erreur "conflict" sinon
     */
    @POST
    @Path("/update")
    @Consumes("application/x-www-form-urlencoded")
    public Response updateCommande(@FormParam("id") int id, @FormParam("numPanier") int numPanier, @FormParam("prix") int prix, @FormParam("loc") String loc, @FormParam("date") String date){

        if( service.updateCommande(id, numPanier, prix, loc, date) )
            return Response.ok("update").build();
        else
            return Response.status( Response.Status.CONFLICT ).build();
    }

    /**
     * Endpoint permettant de supprimer une commande
     * @param id identifiant de la commande à supprimer
     * @return un objet Response indiquant "removed" si la commande a été supprimée ou une erreur "not found" sinon
     */
    @DELETE
    @Path("{id}")
    public Response removeReservation(@PathParam("id") int id){

        if( service.deleteCommande(id) )
            return Response.ok("removed").build();
        else
            return Response.status( Response.Status.NOT_FOUND ).build();
    }
}
