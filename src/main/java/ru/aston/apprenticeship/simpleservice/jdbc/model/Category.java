package ru.aston.apprenticeship.simpleservice.jdbc.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String name;
    private List<Subcategory> subcategories;

    public void addSubcategory(Subcategory subcategory) {
        if (subcategory == null) {
            subcategories = new ArrayList<>();
        }
        subcategories.add(subcategory);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}