package com.chichos_snack_project.model;

import java.sql.Date;
import java.util.Objects;

public class Inventory {
    private int id_inventory;
    private int stock;
    private String lot;
    private java.sql.Date expiration_date;
    private java.sql.Date purchase_date;
    private double purchase_price;
    private Product product;

    public Inventory(int id_inventory, int stock, String lot, Date expiration_date, Date purchase_date, double purchase_price, Product product) {
        this.id_inventory = id_inventory;
        this.stock = stock;
        this.lot = lot;
        this.expiration_date = expiration_date;
        this.purchase_date = purchase_date;
        this.purchase_price = purchase_price;
        this.product = product;
    }

    public int getId_inventory() {
        return id_inventory;
    }

    public void setId_inventory(int id_inventory) {
        this.id_inventory = id_inventory;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return id_inventory == inventory.id_inventory && stock == inventory.stock && Double.compare(purchase_price, inventory.purchase_price) == 0 && Objects.equals(lot, inventory.lot) && Objects.equals(expiration_date, inventory.expiration_date) && Objects.equals(purchase_date, inventory.purchase_date) && Objects.equals(product, inventory.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_inventory, stock, lot, expiration_date, purchase_date, purchase_price, product);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id_inventory=" + id_inventory +
                ", stock=" + stock +
                ", lot='" + lot + '\'' +
                ", expiration_date=" + expiration_date +
                ", purchase_date=" + purchase_date +
                ", purchase_price=" + purchase_price +
                ", product=" + product +
                '}';
    }
}
