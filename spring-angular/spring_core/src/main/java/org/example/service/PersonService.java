package org.example.service;

import org.example.model.Purchase;
import org.example.model.member.Group;
import org.example.model.member.Person;
import org.example.model.products.Category;
import org.example.model.products.Goods;
import org.example.repository.impl.CategoryRepository;
import org.example.repository.impl.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class PersonService {
    @Autowired
    PersonRepository personRepo;
    @Autowired
    CategoryRepository categoryRepo;
    private static int index = 0;
    public void createPerson(){
        Person igor = new Person("igor"+ index++);
        Group someGroup = new Group("some_group"+index++);
        igor.setGroups(Collections.singleton(someGroup));
        Purchase purchase = new Purchase();
        Set<Goods> goods = fillGoodsSet(purchase);
        purchase.setLabel("first_buy");
        purchase.setPerson(igor);
        purchase.setGoodsSet(goods);
        purchase.setInsertTime(new Date());
        igor.setPurchases(Collections.singleton(purchase));
        someGroup.setPerson(igor);
        personRepo.create(igor);
        System.out.println("User create");
    }

    public Set<Goods> fillGoodsSet(Purchase purchase){
        HashSet<Goods> products = new HashSet<>();

        Category dairyCategory = new Category("dairy");
        categoryRepo.create(dairyCategory);

        Goods goods1 = new Goods();
        goods1.setCategory(dairyCategory);
        goods1.setCost(30);
        goods1.setAmount(4);
        goods1.setProductName("milk");
        goods1.setPurchase(purchase);

        Goods goods2 = new Goods();
        goods2.setCategory(dairyCategory);
        goods2.setCost(10);
        goods2.setAmount(10);
        goods2.setProductName("cheese");
        goods2.setPurchase(purchase);

        Goods goods3 = new Goods();
        goods3.setCategory(dairyCategory);
        goods3.setCost(70);
        goods3.setAmount(2);
        goods3.setProductName("kefir");
        goods3.setPurchase(purchase);

        products.add(goods1);
        products.add(goods2);
        products.add(goods3);

        dairyCategory.setGoodsSet(products);




        return products;
    }
    public List<Person> getAll(){
        return personRepo.getAll();
    }

    public Person get(int id){
        return personRepo.get(id);
    }

    public void delete(Person person) {
        personRepo.remove(person);
    }
}
