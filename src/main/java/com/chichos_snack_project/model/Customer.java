package com.chichos_snack_project.model;


import java.sql.Date;
import java.util.Objects;

public class Customer {

    private int id_customer;
    private String name;
    private String phone;
    private java.sql.Date date_register;
    private double amount_available;
    private double amount_used;
    private double amount_total;
    private int id_credit;

    public Customer() {
    }

    public Customer(int id_customer, String name, String phone, Date date_register, double amount_available, double amount_used, double amount_total, int id_credit) {
        this.id_customer = id_customer;
        this.name = name;
        this.phone = phone;
        this.date_register = date_register;
        this.amount_available = amount_available;
        this.amount_used = amount_used;
        this.amount_total = amount_total;
        this.id_credit = id_credit;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_register() {
        return date_register;
    }

    public void setDate_register(Date date_register) {
        this.date_register = date_register;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getAmount_used() {
        return amount_used;
    }

    public void setAmount_used(double amount_used) {
        this.amount_used = amount_used;
    }

    public double getAmount_available() {
        return amount_available;
    }

    public void setAmount_available(double amount_available) {
        this.amount_available = amount_available;
    }

    public double getAmount_total() {
        return amount_total;
    }

    public void setAmount_total(double amount_total) {
        this.amount_total = amount_total;
    }

    public int getId_credit() {
        return id_credit;
    }

    public void setId_credit(int id_credit) {
        this.id_credit = id_credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id_customer == customer.id_customer && Double.compare(amount_available, customer.amount_available) == 0 && Double.compare(amount_used, customer.amount_used) == 0 && Double.compare(amount_total, customer.amount_total) == 0 && id_credit == customer.id_credit && Objects.equals(name, customer.name) && Objects.equals(phone, customer.phone) && Objects.equals(date_register, customer.date_register);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_customer, name, phone, date_register, amount_available, amount_used, amount_total, id_credit);
    }
}
