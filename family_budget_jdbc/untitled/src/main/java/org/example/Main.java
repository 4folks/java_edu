package org.example;

import org.example.db.crud.GroupRepository;
import org.example.db.crud.PersonRepository;
import org.example.db.crud.PurchaseRepository;
import org.example.model.Purchase;
import org.example.model.member.Group;
import org.example.model.member.Person;
import org.example.model.products.Goods;
import org.example.service.PersonService;

import java.util.*;

public class Main {
    static final GroupRepository gRepository = new GroupRepository();
    static final PersonRepository personRepository = new PersonRepository();
    static final PersonService pService = new PersonService();

    static final Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        testCrudLogic();
        menu();

    }
    public static void menu(){
        boolean flag = true;
        while(flag){
            System.out.println("Choose action: Press [0] - exit");
            System.out.println("[1] -- Set person");
            System.out.println("[2] -- Create person");
            System.out.println("[3] -- Show specific person");
            System.out.println("[4] -- Show all person");
            System.out.println("[5] -- Recalculate balance");
            System.out.println("[0] -- Exit");

            int selectedAction = in.nextInt();
            switch(selectedAction){
                case 1 -> pService.setPerson();
                case 2 -> pService.createPerson();
                case 3 -> pService.get();
                case 4 -> pService.getAll();
                case 5 -> pService.recalculateBalance();
                case 0 -> flag = false;
            }
        }
    }
    public static void testCrudLogic(){

        gRepository.create(new Group("some_group1"));
        gRepository.create(new Group("some_group2"));
        gRepository.create(new Group("some_group3"));

        Group group1 = new Group("children");
        Group group2 = new Group("adult");

        List<Group> glist = new ArrayList<>();
        Person igor = new Person("igor");
        igor.setMonthlyBudget(400);
        igor.setBalance(400);

        glist.add(group1);
        glist.add(group2);
        igor.setGroups(glist);
        personRepository.create(igor);

        Person igor1 = personRepository.get(new Person("igor"));
        System.out.println(igor1);

        List<Group> groupList = gRepository.getAll();
        System.out.println(groupList);
        Map<Goods, Integer> goodsAndCount = new HashMap<>();
        goodsAndCount.put(new Goods(1),20);
        goodsAndCount.put(new Goods(2),12);
        goodsAndCount.put(new Goods(3),50);

        Purchase purchase = new Purchase();
        purchase.setLabel("midnight");
        purchase.setPersonId(1);
        purchase.setTimestamp(new Date());
        purchase.setGoodsAndCount(goodsAndCount);

        PurchaseRepository purchaseRepository = new PurchaseRepository();

        purchaseRepository.create(purchase);
        List<Purchase> all = purchaseRepository.getAll(new Person(1));

        for (Purchase p : all) {
            System.out.println(p);
        }
    }
}