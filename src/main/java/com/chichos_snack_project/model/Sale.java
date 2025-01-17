package com.chichos_snack_project.model;


import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Sale {
   private int id_sale;
   private Employee employee;
   private Customer customer;
   private java.sql.Date sale_date;
   private double amount;
   private int count;
   private List<Product> productList;


    public Sale() {

    }

    public Sale(int id_sale, Employee employee, Customer customer, Date sale_date, double amount, int count,List<Product> productList) {
        this.id_sale = id_sale;
        this.employee = employee;
        this.sale_date = sale_date;
        this.customer = customer;
        this.amount = amount;
        this.count = count;
        this.productList = productList;

    }

    public int getId_sale() {
        return id_sale;
    }

    public void setId_sale(int id_sale) {
        this.id_sale = id_sale;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getSale_date() {
        return sale_date;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return id_sale == sale.id_sale;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_sale);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id_sale=" + id_sale +
                ", employee=" + employee +
                ", customer=" + customer +
                ", sale_date=" + sale_date +
                ", amount=" + amount +
                ", count=" + count +
                ", productList=" + productList +
                '}';
    }
}
