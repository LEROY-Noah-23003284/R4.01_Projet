package fr.univamu.iut.prodanduser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.sql.Connection;
import java.sql.DriverManager;

@ApplicationPath("/api")
@ApplicationScoped
public class UserApplication extends Application {
    /**
     * Méthode appelée par l'API CDI pour injecter la connection à la base de données au moment de la création
     * de la ressource
     * @return un objet implémentant l'interface UserRepositoryInterface utilisée
     *          pour accéder aux données des livres, voire les modifier
     */
    @Produces
    @ApplicationScoped
    public UserRepositoryInterface openDbConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://mysql-thelu.alwaysdata.net/thelu_pu_db";
            String user = "thelu_pu";
            String password = "theluNathan2005";

            // Test de connexion
            Connection testConn = DriverManager.getConnection(url, user, password);
            testConn.close();

            return new UserRepositoryMariadb(url, user, password);
        } catch (Exception e) {
            throw new IllegalStateException("Échec de connexion à la DB: " + e.getMessage(), e);
        }
    }
}