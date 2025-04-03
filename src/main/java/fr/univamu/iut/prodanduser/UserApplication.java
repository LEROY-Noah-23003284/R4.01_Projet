package fr.univamu.iut.prodanduser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.sql.Connection;
import java.sql.DriverManager;

@ApplicationPath("/api")
@ApplicationScoped
public class UserApplication extends Application {

    @Produces
    @ApplicationScoped
    public UserRepositoryInterface openDbConnection() {
        try {
            //Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://mysql-thelu.alwaysdata.net/thelu_pu_db";
            String user = "thelu_pu";
            String password = "theluNathan2005";

            // Test de connexion
            Connection testConn = DriverManager.getConnection(url, user, password);
            testConn.close();

            return new UserRepositoryMariadb(url, user, password);
        } catch (Exception e) {
            throw new IllegalStateException("Échec de connexion à la base de données: " + e.getMessage(), e);
        }
    }
}