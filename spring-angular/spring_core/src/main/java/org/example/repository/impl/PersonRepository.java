package org.example.repository.impl;

import org.example.model.member.Person;
import org.example.repository.GeneralCrud;

public class PersonRepository extends GeneralCrud<Person> {
    public PersonRepository() {
        super(Person.class);
    }
}
