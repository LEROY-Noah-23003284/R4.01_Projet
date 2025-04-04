package fr.univamu.iut.paniers;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Service pour gérer la logique métier des paniers.
 * Il interagit avec le repository des paniers pour effectuer des opérations telles que la création et la suppression de paniers.
 * Fournit des méthodes pour manipuler ces paniers.
 */
public class PanierService {

    protected PanierRepositoryInterface panierRepo;

    /**
     * Constructeur du service de gestion des paniers.
     *
     * @param panierRepo le repository des paniers
     */
    public PanierService(PanierRepositoryInterface panierRepo) {
        this.panierRepo = panierRepo;
    }

    /**
     * Récupère tous les paniers au format JSON.
     *
     * @return une chaîne de caractères JSON représentant tous les paniers
     */
    public String getAllPaniersJSON() {
        ArrayList<Panier> allPaniers = panierRepo.getAllPaniers();
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allPaniers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Récupère un panier spécifique par son ID au format JSON.
     *
     * @param id l'ID du panier
     * @return une chaîne JSON représentant le panier
     */
    public String getPanierJSON(int id) {
        String result = null;
        Panier myPanier = panierRepo.getPanier(id);
        if (myPanier != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myPanier);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Met à jour les informations d'un panier.
     *
     * @param id l'ID du panier à mettre à jour
     * @param panier l'objet panier avec les nouvelles informations
     * @return true si la mise à jour a été effectuée
     */
    public boolean updatePanier(int id, Panier panier) {
        return panierRepo.updatePanier(id, panier.getName(), panier.getDatdemàj(), panier.getPrice(), panier.getQuantity());
    }

    /**
     * Crée un nouveau panier.
     *
     * @param panier le panier à créer
     * @return le panier créé
     */
    public Panier createPanier(Panier panier) {
        return panierRepo.createPanier(panier);
    }

    /**
     * Supprime un panier par son ID.
     *
     * @param id l'ID du panier à supprimer
     */
    public void deletePanier(int id) {
        panierRepo.deletePanier(id);
    }
}
