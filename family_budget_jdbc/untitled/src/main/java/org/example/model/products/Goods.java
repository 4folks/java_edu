package org.example.model.products;

import java.util.Date;

public class Goods {
    private int id;
    private String productName;
    private long cost;

    private Category category;


    public Goods() {
    }

    public Goods(String productName, Category category) {
        this.productName = productName;
        this.category = category;
    }

    public Goods(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", cost=" + cost +
                ", category=" + category +
                '}';
    }
}
