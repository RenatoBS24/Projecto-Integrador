package com.chichos_snack_project.model;

import java.sql.Date;
import java.util.Objects;

public class Notification {

    private int id_notification;
    private Product product;
    private String message;
    private String affair;
    private java.sql.Date date;

    public Notification() {
    }

    public Notification(int id_notification, Product product, String message, String affair, Date date) {
        this.id_notification = id_notification;
        this.product = product;
        this.message = message;
        this.affair = affair;
        this.date = date;
    }

    public int getId_notification() {
        return id_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAffair() {
        return affair;
    }

    public void setAffair(String affair) {
        this.affair = affair;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return id_notification == that.id_notification && Objects.equals(product, that.product) && Objects.equals(message, that.message) && Objects.equals(affair, that.affair) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_notification, product, message, affair, date);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id_notification=" + id_notification +
                ", product=" + product +
                ", message='" + message + '\'' +
                ", affair='" + affair + '\'' +
                ", date=" + date +
                '}';
    }
}
