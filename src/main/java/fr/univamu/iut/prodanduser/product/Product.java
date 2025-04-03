package fr.univamu.iut.prodanduser.product;

/**
 * Classe représentant un utilisateur du système.
 */
public class Product {

    /**
     * Indique si l'utilisateur est administrateur (1 pour admin, 0 pour utilisateur standard).
     */
    protected int id;

    /**
     * Nom de l'utilisateur.
     */
    protected String name;

    /**
     * Mot de passe de l'utilisateur.
     */
    protected String type;

    /**
     * Adresse email de l'utilisateur.
     */
    protected String season;

    protected float price;

    /**
     * Constructeur par défaut.
     */
    public Product() {
    }

    /**
     * Constructeur de la classe Product.
     * @param season Adresse email de l'utilisateur.
     * @param name Nom de l'utilisateur.
     * @param id Statut administrateur (1 pour admin, 0 pour utilisateur standard).
     * @param type Mot de passe de l'utilisateur.
     */
    public Product(int id, String name, String type, String season, float price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.season = season;
        this.price = price;
    }

    /**
     * Obtient le statut administrateur de l'utilisateur.
     * @return 1 si l'utilisateur est admin, 0 sinon.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtient le nom de l'utilisateur.
     * @return Nom de l'utilisateur.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtient le mot de passe de l'utilisateur.
     * @return Mot de passe de l'utilisateur.
     */
    public String getType() {
        return type;
    }

    /**
     * Obtient l'adresse email de l'utilisateur.
     * @return Adresse email de l'utilisateur.
     */
    public String getSeason() {
        return season;
    }

    public float getPrice() {
        return price;
    }

    /**
     * Définit le statut administrateur de l'utilisateur.
     * @param id 1 pour id, 0 pour utilisateur standard.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Définit le nom de l'utilisateur.
     * @param name Nom de l'utilisateur.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     * @param type Mot de passe de l'utilisateur.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Définit l'adresse email de l'utilisateur.
     * @param season Adresse email de l'utilisateur.
     */
    public void setSeason(String season) {
        this.season = season;
    }


    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Retourne une représentation textuelle de l'utilisateur.
     * @return Chaîne de caractères représentant l'utilisateur.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", season='" + season + '\'' +
                ", price=" + price +
                "}";
    }
}
