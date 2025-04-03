package fr.univamu.iut.paniers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class PanierApplication extends Application {

    @Produces
    @ApplicationScoped
    public PanierRepositoryInterface openDbConnection() {
        try {
            return new PanierRepositoryMariadb("jdbc:mariadb://mysql-zane.alwaysdata.net/zane_library_db", "zane_library", "BadissZane");
        }
        catch (Exception e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données", e);
        }
    }

}