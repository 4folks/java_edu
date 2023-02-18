package org.example.web;

import org.example.model.member.Person;
import org.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainPage {
    @Autowired
    private PersonService personService;

    @GetMapping("/create")
    public void createPerson(){
        personService.createPerson();
    }

    @DeleteMapping("person/{id}")
    public void deletePerson(@PathVariable int id){
        personService.delete(new Person(id));
    }

    @GetMapping("/personList")
    public List<Person> getAll(){
        List<Person> all = personService.getAll();
        System.out.println(all);
        return all;
    }
}
