package fr.univamu.iut.prodanduser.product;

/**
 * Classe représentant un produit du système.
 */
public class Product {

    /**
     * Identifiant unique du produit.
     */
    protected int id;

    /**
     * Nom du produit.
     */
    protected String name;

    /**
     * Type du produit.
     */
    protected String type;

    /**
     * Saison associée au produit.
     */
    protected String season;

    /**
     * Prix du produit.
     */
    protected float price;

    /**
     * Constructeur par défaut.
     */
    public Product() {
    }

    /**
     * Constructeur de la classe Product.
     * @param id Identifiant unique du produit.
     * @param name Nom du produit.
     * @param type Type du produit.
     * @param season Saison associée au produit.
     * @param price Prix du produit.
     */
    public Product(int id, String name, String type, String season, float price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.season = season;
        this.price = price;
    }

    /**
     * Obtient l'identifiant du produit.
     * @return Identifiant du produit.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtient le nom du produit.
     * @return Nom du produit.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtient le type du produit.
     * @return Type du produit.
     */
    public String getType() {
        return type;
    }

    /**
     * Obtient la saison associée au produit.
     * @return Saison du produit.
     */
    public String getSeason() {
        return season;
    }

    /**
     * Obtient le prix du produit.
     * @return Prix du produit.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Définit l'identifiant du produit.
     * @param id Nouvel identifiant du produit.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Définit le nom du produit.
     * @param name Nouveau nom du produit.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Définit le type du produit.
     * @param type Nouveau type du produit.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Définit la saison du produit.
     * @param season Nouvelle saison du produit.
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * Définit le prix du produit.
     * @param price Nouveau prix du produit.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Retourne une représentation textuelle du produit.
     * @return Chaîne de caractères représentant le produit.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", season='" + season + '\'' +
                ", price=" + price +
                '}';
    }
}
