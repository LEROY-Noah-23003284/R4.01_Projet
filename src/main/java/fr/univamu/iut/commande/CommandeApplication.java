package fr.univamu.iut.commande;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class CommandeApplication extends Application {
    /**
     * Méthode appelée par l'API CDI pour injecter la connection à la base de données au moment de la création
     * de la ressource
     * @return un objet implémentant l'interface CommandeRepositoryInterface utilisée
     *          pour accéder aux données des commandes, voire les modifier
     */
    @Produces
    private CommandeRepositoryInterface openDbConnection(){
        CommandeRepositoryMariadb db = null;

        try{
            db = new CommandeRepositoryMariadb("jdbc:mariadb://mysql-miniere.alwaysdata.net/miniere_commande_db", "miniere_commande", "MMR4.01CC2");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     * @param commandeRepo la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void closeDbConnection(@Disposes CommandeRepositoryInterface commandeRepo ) {
        commandeRepo.close();
    }
}
