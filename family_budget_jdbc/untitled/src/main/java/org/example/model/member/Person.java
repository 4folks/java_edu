package org.example.model.member;

import java.util.List;

public class Person {
    private int id;
    private String name;
    private long monthlyBudget;
    private long balance;

    private List<Group> groups;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(long monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
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
                '}';
    }
}
