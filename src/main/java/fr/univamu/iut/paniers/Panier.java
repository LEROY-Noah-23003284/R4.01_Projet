package fr.univamu.iut.paniers;

import java.sql.Date;
import java.util.Objects;

/**
 * Représente un panier dans l'application.
 * Contient les informations de base sur un panier, comme son ID, son nom, la date de demande, le prix et la quantité.
 */
public class Panier {

    protected int id;
    protected String name;
    protected Date datdemàj;
    protected int price;
    protected int quantity;

    /**
     * Constructeur pour initialiser un panier avec les informations nécessaires.
     *
     * @param name le nom du panier
     * @param datdemàj la date de demande
     * @param price le prix du panier
     * @param quantity la quantité d'articles dans le panier
     */
    public Panier(String name, Date datdemàj, int price, int quantity) {
        this.name = name;
        this.datdemàj = datdemàj;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Retourne l'ID du panier.
     *
     * @return l'ID du panier
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le nom du panier.
     *
     * @return le nom du panier
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne la date de demande du panier.
     *
     * @return la date de demande du panier
     */
    public java.sql.Date getDatdemàj() {
        return datdemàj;
    }

    /**
     * Retourne le prix du panier.
     *
     * @return le prix du panier
     */
    public int getPrice() {
        return price;
    }

    /**
     * Retourne la quantité d'articles dans le panier.
     *
     * @return la quantité d'articles dans le panier
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Définit le nom du panier.
     *
     * @param name le nom du panier
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Définit la date de demande du panier.
     *
     * @param datdemàj la date de demande
     */
    public void setDatdemàj(Date datdemàj) {
        this.datdemàj = datdemàj;
    }

    /**
     * Définit le prix du panier.
     *
     * @param price le prix du panier
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Définit la quantité d'articles dans le panier.
     *
     * @param quantity la quantité d'articles
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Définit l'ID du panier.
     *
     * @param id l'ID du panier
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Panier panier = (Panier) obj;
        return id == panier.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    /**
     * Retourne une chaîne de caractères représentant le panier.
     *
     * @return une chaîne représentant les détails du panier
     */
    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", datdemàj=" + datdemàj +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
