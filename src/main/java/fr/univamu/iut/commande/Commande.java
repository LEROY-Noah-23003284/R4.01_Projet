package fr.univamu.iut.commande;

/**
 * Classe représentant une commande
 */
public class Commande {
    /**
     * Identifiant de la commande
     */
    protected int id;

    /**
     * Numéro du panier de la commande
     */
    protected int numPanier;

    /**
     * Prix de la commande
     */
    protected int prix;

    /**
     * Localisation du relai
     */
    protected String loc;

    /**
     * Date du retrait de la commande
     */
    protected String date;

    public Commande() {
    }

    public Commande(int id, int numPanier, int prix, String loc, String date) {
        this.id = id;
        this.numPanier = numPanier;
        this.prix = prix;
        this.loc = loc;
        this.date = date;
    }

    /**
     * Méthode permettant d'accéder à l'identifiant de la commande
     * @return un entier correspondant à l'identifiant de la commande
     */
    public int getId() {
        return id;
    }

    /**
     * Méthode permettant de modifier l'identifiant de la commande
     * @param id un entier avec l'identifiant à utiliser
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Méthode permettant d'accéder au panier de la commande
     * @return un entier avec le numéro du panier
     */
    public int getPanier() {
        return numPanier;
    }

    /**
     * Méthode permettant de modifier le numéro du panier de la commande
     * @param numPanier un entier avec le numéro du panier
     */
    public void setPanier(int numPanier) {
        this.numPanier = numPanier;
    }

    /**
     * Méthode permettant d'accéder au prix de la commande
     * @return un entier avec le prix de la commande
     */
    public int getPrix() {
        return prix;
    }

    /**
     * Méthode permettant de modifier la référence de la commande
     * @param prix un entier correspondant au prix du panier
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    /**
     * Méthode permettant d'accéder à la localisation du relai
     * @return une chaîne de caractères avec la localisation du relai
     */
    public String getLoc() {
        return loc;
    }

    /**
     * Méthode permettant de modifier la référence de la commande
     * @param loc une chaîne de caractères avec la localisation du relai
     */
    public void setLoc(String loc) {
        this.loc = loc;
    }

    /**
     * Méthode permettant d'accéder à la date de retrait de la commande
     * @return une chaîne de caractères avec la date du retrait de la commande
     */
    public String getDate() {
        return date;
    }

    /**
     * Méthode permettant de modifier la date de retrait de la commande
     * @param date une chaîne de caractères avec la date à utiliser
     */
    public void setDate(String date) {
        this.date = date;
    }
}
