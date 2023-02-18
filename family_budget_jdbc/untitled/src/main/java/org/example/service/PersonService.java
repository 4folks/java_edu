package org.example.service;

import org.example.db.crud.GroupRepository;
import org.example.db.crud.PersonRepository;
import org.example.db.crud.PurchaseRepository;
import org.example.model.Purchase;
import org.example.model.member.Group;
import org.example.model.member.Person;
import org.example.model.products.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PersonService {
    private final PersonRepository pRepo = new PersonRepository();
    private final GroupRepository gRepo = new GroupRepository();
    
    private final PurchaseRepository purchaseRepository = new PurchaseRepository();
    
    private Person person;
    private final Scanner in = new Scanner(System.in);
    
    public void setPerson(){
        System.out.println("<Choose user:>");
        List<Person> all = pRepo.getAll();
        all.forEach(System.out::println);
        while(true) {
            System.out.print("\nInput index:");
            int index = in.nextInt();
            person = all.get(index);
            if(person.getName()!=null){
                break;
            }
        }
    }

    public void createPerson(){
        System.out.println("<Create Person method>");
        System.out.printf("Input username: ");
        String name = in.next();
        System.out.printf("Input budget: ");
        int monthly = in.nextInt();

        Person person = new Person();
        person.setName(name);
        person.setMonthlyBudget(monthly);


        List<Group> all = gRepo.getAll();
        System.out.println("Group list:");
        for (Group g :all) {
            System.out.println(g.getGroupName());
        }

        System.out.print("Input index:");
        int index = in.nextInt();
        List<Group> groups = new ArrayList<>();
        groups.add(all.get(index));
        person.setGroups(groups);

        pRepo.create(person);

    }

    public void deletePerson(){

    }
    
    public void recalculateBalance(){
        List<Purchase> all = purchaseRepository.getAll(person);
        
        int totalCost = 0;
        for (Purchase pch: all ) {
            Map<Goods, Integer> goodsAndCount = pch.getGoodsAndCount();
            for (Map.Entry<Goods, Integer> row: goodsAndCount.entrySet()) {
                long amountOfPurchase = row.getKey().getCost() * row.getValue();
                totalCost += amountOfPurchase;
            }
        }
        long newBalance = person.getBalance() - totalCost;
        person.setBalance(newBalance);
        pRepo.update(person);

        System.out.println("<RecalculatedBalance:>");
        System.out.println(pRepo.get(person));
    }

    public void get(){
        System.out.println("<Show specific person>");
        String name = in.next();
        Person person = pRepo.get(new Person(name));
        System.out.println(person);
    }

    public void getAll(){
        System.out.println("<Show all person records>");
        List<Person> personList = pRepo.getAll();
        System.out.println(personList);
    }
    
}
