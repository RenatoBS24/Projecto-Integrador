package com.chichos_snack_project.model;

import java.util.Objects;

public class UnitOfMeasurement {

    private int id_unit_of_measurement;
    private String name_unit_of_measurement;
    private String description;

    public UnitOfMeasurement() {
    }

    public UnitOfMeasurement(int id_unit_of_measurement, String name_unit_of_measurement, String description) {
        this.id_unit_of_measurement = id_unit_of_measurement;
        this.name_unit_of_measurement = name_unit_of_measurement;
        this.description = description;
    }

    public int getId_unit_of_measurement() {
        return id_unit_of_measurement;
    }

    public void setId_unit_of_measurement(int id_unit_of_measurement) {
        this.id_unit_of_measurement = id_unit_of_measurement;
    }

    public String getName_unit_of_measurement() {
        return name_unit_of_measurement;
    }

    public void setName_unit_of_measurement(String name_unit_of_measurement) {
        this.name_unit_of_measurement = name_unit_of_measurement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitOfMeasurement that = (UnitOfMeasurement) o;
        return id_unit_of_measurement == that.id_unit_of_measurement && Objects.equals(name_unit_of_measurement, that.name_unit_of_measurement) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_unit_of_measurement, name_unit_of_measurement, description);
    }
}
