package org.example.model;

import org.example.model.products.Goods;

import java.util.Date;
import java.util.Map;

public class Purchase {
    private int id;

    private String label;
    private int personId;
    private Map<Goods,Integer> goodsAndCount;
    private Date timestamp;

    public Purchase(int id, int personId, Date timestamp) {
        this.id = id;
        this.personId = personId;
        this.timestamp = timestamp;
    }

    public Purchase(String label, int personId) {
        this.label = label;
        this.personId = personId;
    }

    public Purchase() {
    }

    public Purchase(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public Map<Goods, Integer> getGoodsAndCount() {
        return goodsAndCount;
    }

    public void setGoodsAndCount(Map<Goods, Integer> goodsAndCount) {
        this.goodsAndCount = goodsAndCount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", personId=" + personId +
                ", goodsAndCount=" + goodsAndCount +
                ", timestamp=" + timestamp +
                '}';
    }
}
