package com.chichos_snack_project.model;

import java.sql.Date;
import java.util.Objects;

public class Employee {
    private int id_employee;
    private String name;
    private String lastname;
    private double salary;
    private String dni;
    private java.sql.Date entry_date;
    private String phone;

    public Employee(){

    }

    public Employee(int id_employee, String name, String lastname, double salary, String dni, Date entry_date, String phone) {
        this.id_employee = id_employee;
        this.name = name;
        this.lastname = lastname;
        this.salary = salary;
        this.dni = dni;
        this.entry_date = entry_date;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id_employee == employee.id_employee && Double.compare(salary, employee.salary) == 0 && Objects.equals(name, employee.name) && Objects.equals(lastname, employee.lastname) && Objects.equals(dni, employee.dni) && Objects.equals(entry_date, employee.entry_date) && Objects.equals(phone, employee.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_employee, name, lastname, salary, dni, entry_date, phone);
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id_employee=" + id_employee +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", salary=" + salary +
                ", dni='" + dni + '\'' +
                ", entry_date=" + entry_date +
                ", phone='" + phone + '\'' +
                '}';
    }
}
