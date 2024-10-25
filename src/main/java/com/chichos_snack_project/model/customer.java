package com.chichos_snack_project.model;

import java.time.LocalDate;
import java.util.Objects;

public class customer {

    private String name;
    private LocalDate register_date;

    public customer() {
    }

    public customer(String name, LocalDate register_date) {
        this.name = name;
        this.register_date = register_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegister_date() {
        return register_date;
    }

    public void setRegister_date(LocalDate register_date) {
        this.register_date = register_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        customer customer = (customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(register_date, customer.register_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, register_date);
    }

    @Override
    public String toString() {
        return "customer{" +
                "name='" + name + '\'' +
                ", register_date=" + register_date +
                '}';
    }
}
