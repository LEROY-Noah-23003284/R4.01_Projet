package fr.univamu.iut.paniers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/paniers")
@ApplicationScoped
public class PanierResource {

    private PanierService service;

    public PanierResource() {}

    @Inject
    public PanierResource(PanierRepositoryInterface panierRepo) {
        this.service = new PanierService(panierRepo);
    }

    @GET
    @Produces("application/json")
    public String getAllPaniers() {
        return service.getAllPaniersJSON();
    }

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


    @POST
    @Consumes("application/json")
    public Response createPanier(Panier panier) {
        Panier createdPanier = service.createPanier(panier);
        return Response.status(Response.Status.CREATED).entity(createdPanier).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updatePanier(@PathParam("id") int id, Panier panier) {
        if (!service.updatePanier(id, panier)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePanier(@PathParam("id") int id) {
        service.deletePanier(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
