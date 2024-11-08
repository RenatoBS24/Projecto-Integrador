package com.chichos_snack_project.model;

import java.util.Objects;

public class Product {
    private int id_product;
    private String name;
    private double price;
    private int stock;
    private Category category;
    public Product(){
    }

    public Product(int id_product, String name, double price, int stock, Category category) {
        this.id_product = id_product;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id_product == product.id_product && Double.compare(price, product.price) == 0 && stock == product.stock && Objects.equals(name, product.name) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_product, name, price, stock, category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id_product=" + id_product +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", category=" + category +
                '}';
    }
}
