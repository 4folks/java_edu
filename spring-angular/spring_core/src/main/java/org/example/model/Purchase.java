package org.example.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.example.model.member.Person;
import org.example.model.products.Goods;

import java.util.Date;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","person"})
public class Purchase {
    private int id;

    private String label;

    private Person person;

    private Set<Goods> goodsSet;

    private Date insertTime;

    public Purchase(int id, Person person, Date insertTime) {
        this.id = id;
        this.person = person;
        this.insertTime = insertTime;
    }

    public Purchase(String label, Person person) {
        this.label = label;
        this.person = person;
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


    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Goods> getGoodsSet() {
        return goodsSet;
    }

    public void setGoodsSet(Set<Goods> goodsSet) {
        this.goodsSet = goodsSet;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", goods=" + goodsSet +
                ", insertTime=" + insertTime +
                '}';
    }
}

