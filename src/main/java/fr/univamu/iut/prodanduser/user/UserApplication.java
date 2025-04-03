package fr.univamu.iut.prodanduser.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe principale de l'application gérant la configuration et l'accès aux données des utilisateurs.
 * Elle définit le point d'entrée de l'API REST et fournit une connexion à la base de données.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class UserApplication extends Application {

    /**
     * Méthode appelée par le conteneur CDI pour injecter une connexion à la base de données
     * lors de la création des ressources.
     *
     * @return Une instance de {@link UserRepositoryInterface} permettant d'accéder aux données
     *         des utilisateurs et de les modifier.
     * @throws IllegalStateException si la connexion à la base de données échoue.
     */
    @Produces
    @ApplicationScoped
    public UserRepositoryInterface openDbConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://mysql-thelu.alwaysdata.net/thelu_pu_db";
            String user = "thelu_pu";
            String password = "theluNathan2005";

            // Test de connexion à la base de données
            Connection testConn = DriverManager.getConnection(url, user, password);
            testConn.close();

            return new UserRepositoryMariadb(url, user, password);
        } catch (Exception e) {
            throw new IllegalStateException("Échec de connexion à la base de données: " + e.getMessage(), e);
        }
    }
}
