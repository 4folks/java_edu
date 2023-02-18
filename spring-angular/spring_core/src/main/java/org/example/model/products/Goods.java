package org.example.model.products;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.model.Purchase;

import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","purchase","category"})
public class Goods {
    private int id;
    private String productName;
    private int cost;


    private Purchase purchase;


    private int amount;

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

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
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
                ", amount=" + amount +
                '}';
    }
}
