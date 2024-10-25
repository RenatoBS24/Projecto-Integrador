package com.chichos_snack_project.model;

import java.time.LocalDate;
import java.util.Objects;

public class sale {
    private int id;
    private LocalDate sale_date;
    private double amount;

    public sale(){

    }

    public sale(int id, LocalDate sale_date, double amount) {
        this.id = id;
        this.sale_date = sale_date;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getSale_date() {
        return sale_date;
    }

    public void setSale_date(LocalDate sale_date) {
        this.sale_date = sale_date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        sale sale = (sale) o;
        return id == sale.id && Double.compare(amount, sale.amount) == 0 && Objects.equals(sale_date, sale.sale_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sale_date, amount);
    }

    @Override
    public String toString() {
        return "sale{" +
                "id=" + id +
                ", sale_date=" + sale_date +
                ", amount=" + amount +
                '}';
    }
}
