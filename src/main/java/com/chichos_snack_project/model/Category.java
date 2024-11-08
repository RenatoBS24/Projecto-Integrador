package com.chichos_snack_project.model;

import java.util.Objects;

public class Category {

    private int id_category;
    private String name_category;
    private String description_category;

    public Category() {

    }

    public Category(int id_category, String name_category, String description_category) {
        this.id_category = id_category;
        this.name_category = name_category;
        this.description_category = description_category;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }

    public String getDescription_category() {
        return description_category;
    }

    public void setDescription_category(String description_category) {
        this.description_category = description_category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id_category == category.id_category && Objects.equals(name_category, category.name_category) && Objects.equals(description_category, category.description_category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_category, name_category, description_category);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id_category=" + id_category +
                ", name_category='" + name_category + '\'' +
                ", description_category='" + description_category + '\'' +
                '}';
    }
}
