    package fr.univamu.iut.prodanduser;

    import jakarta.enterprise.context.ApplicationScoped;
    import jakarta.inject.Inject;
    import jakarta.ws.rs.*;

@Path("/user")
@ApplicationScoped
public class UserResource {
    /**
     * Service utilisé pour accéder aux données des livres et récupérer/modifier leurs informations
     */
    @Inject
    private UserService service;


    /**
     * Enpoint permettant de publier de tous les livres enregistrés
     * @return la liste des livres (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return service.getAllUsersJSON();
    }

    /**
     * Récupère tous les utilisateurs ayant le rôle "Client" sous forme de JSON.
     * @return Une chaîne de caractères contenant les utilisateurs ayant le rôle "Client" au format JSON.
     */
    @GET
    @Path("/nonAdmin")
    @Produces("application/json")
    public String getAllUserNonAdmin() {
        return service.getAllUsersNonAdminJSON();
    }

    /**
     * Récupère tous les utilisateurs ayant le rôle "Gestionnaire" sous forme de JSON.
     *
     * @return Une chaîne de caractères contenant les utilisateurs ayant le rôle "Gestionnaire" au format JSON.
     */
    @GET
    @Path("/admin")
    @Produces("application/json")
    public String getAllUserAdmin() {
        return service.getAllUsersAdminJSON();
    }


    /**
     * Endpoint permettant de publier les informations d'un livre dont la référence est passée paramètre dans le chemin
     * @param mail référence du livre recherché
     * @return les informations du livre recherché au format JSON
     */
    @GET
    @Path("{mail}")
    @Produces("application/json")
    public String getUser( @PathParam("mail") String mail){

        String result = service.getUserJSON(mail);
        if( result == null )
            throw new NotFoundException();

        return result;
    }
}
