package fr.univamu.iut.commande;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'accès aux données)
 */
public class CommandeService {    
    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les commandes
     */
    protected CommandeRepositoryInterface commandeRepo ;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param commandeRepo objet implémentant l'interface d'accès aux données des commandes
     */
    public CommandeService(CommandeRepositoryInterface commandeRepo) {
        this.commandeRepo = commandeRepo;
    }

    /**
     * Méthode retournant les informations sur les commandes au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllCommandesJSON(){

        ArrayList<Commande> allCommandes = commandeRepo.getAllCommandes();

        // création du json et conversion de la liste de commandes
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allCommandes);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur une commande recherchée
     * @param id la référence de la commande recherchée
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getCommandeJSON( int id ){
        String result = null;
        Commande myCommande = commandeRepo.getCommande(id);

        // si la commande a été trouvé
        if( myCommande != null ) {

            // création du json et conversion de la commande
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myCommande);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant d'enregistrer une commande
     * @param numPanier numéro du panier
     * @param loc localisation du relai
     * @param date date de retrait
     * @return true si la commande a été enregistrée, false sinon
     */
    public boolean registerCommande(int numPanier, int prix, String loc, String date) {
        return commandeRepo.registerCommande(numPanier, prix, loc, date);
    }
}

