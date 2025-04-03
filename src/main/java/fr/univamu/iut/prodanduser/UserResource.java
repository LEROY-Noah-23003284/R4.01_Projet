package fr.univamu.iut.prodanduser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource RESTful pour gérer les utilisateurs.
 * Fournit des endpoints pour récupérer, ajouter, mettre à jour et supprimer des utilisateurs.
 */
@Path("/user")
@ApplicationScoped
public class UserResource {
    /**
     * Service utilisé pour accéder aux données des utilisateurs et les manipuler.
     */
    @Inject
    private UserService service;

    /**
     * Endpoint permettant de récupérer tous les utilisateurs enregistrés au format JSON.
     * @return Une chaîne JSON représentant la liste des utilisateurs.
     */
    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return service.getAllUsersJSON();
    }

    /**
     * Endpoint permettant de récupérer tous les utilisateurs non administrateurs au format JSON.
     * @return Une chaîne JSON contenant les utilisateurs n'ayant pas le rôle administrateur.
     */
    @GET
    @Path("/nonAdmin")
    @Produces("application/json")
    public String getAllUserNonAdmin() {
        return service.getAllUsersNonAdminJSON();
    }

    /**
     * Endpoint permettant de récupérer tous les utilisateurs administrateurs au format JSON.
     * @return Une chaîne JSON contenant les utilisateurs ayant le rôle administrateur.
     */
    @GET
    @Path("/admin")
    @Produces("application/json")
    public String getAllUserAdmin() {
        return service.getAllUsersAdminJSON();
    }

    /**
     * Endpoint permettant de récupérer un utilisateur spécifique selon son email.
     * @param mail L'email de l'utilisateur recherché.
     * @return Une chaîne JSON contenant les informations de l'utilisateur.
     * @throws NotFoundException Si l'utilisateur n'est pas trouvé.
     */
    @GET
    @Path("{mail}")
    @Produces("application/json")
    public String getUser(@PathParam("mail") String mail) {
        String result = service.getUserJSON(mail);
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    /**
     * Endpoint permettant de mettre à jour un utilisateur existant.
     * @param user L'objet utilisateur contenant les nouvelles informations.
     * @return Une réponse HTTP confirmant la mise à jour.
     */
    @PUT
    @Path("/update")
    @Consumes("application/json")
    public Response updateUser(User user) {
        service.updateUser(user);
        return Response.ok("updated").build();
    }

    /**
     * Endpoint permettant de supprimer un utilisateur existant.
     * @param user L'objet utilisateur contenant l'email de l'utilisateur à supprimer.
     * @return Une réponse HTTP confirmant la suppression.
     */
    @PUT
    @Path("/delete")
    @Consumes("application/json")
    public Response deleteUser(User user) {
        service.deleteUser(user.getMail());
        return Response.ok("deleted").build();
    }

    /**
     * Endpoint permettant d'ajouter un nouvel utilisateur.
     * @param user L'objet utilisateur à ajouter.
     * @return Une réponse HTTP confirmant l'ajout.
     */
    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addUser(User user) {
        service.createUser(user);
        return Response.ok("add").build();
    }
}
