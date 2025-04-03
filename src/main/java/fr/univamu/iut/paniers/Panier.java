package fr.univamu.iut.paniers;

//import fr.univamu.iut.paniers.Produit;

import java.util.ArrayList;
import java.util.Date;

/***
 * Classe Panier
 */
public class Panier {

    protected int id;
    protected String name;
    protected Date datdemàj;
    protected int price;
    protected int quantity;
//    protected ArrayList<Produit> produits = null;

    public Panier(String name, Date datdemàj, int price, int quantity) {
        this.name = name;
        this.datdemàj = datdemàj;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDatdemàj() {
        return datdemàj;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDatdemàj(Date datdemàj) {
        this.datdemàj = datdemàj;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public ArrayList<Produit> getProduits() {
//        return produits;
//    }

//    public void setProduits(ArrayList<Produit> produits) {
//        this.produits = produits;
//    }

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