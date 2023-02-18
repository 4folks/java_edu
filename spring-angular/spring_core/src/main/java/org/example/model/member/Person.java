package org.example.model.member;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.example.model.Purchase;

import java.util.List;
import java.util.Set;

public class Person {
    private int id;
    private String name;
    private int monthlyBudget;
    private int balance;

    private Set<Group> groups;

    private Set<Purchase> purchases;


    public Person(String name) {
        this.name = name;
    }

    public Person() {

    }

    public Person(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(int monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", monthlyBudget=" + monthlyBudget +
                ", balance=" + balance +
                ", groups=" + groups +
                ", purchase=" + purchases +
                '}';
    }
}