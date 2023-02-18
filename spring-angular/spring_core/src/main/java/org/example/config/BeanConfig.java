package org.example.config;

import org.example.db_config.HibernateUtil;
import org.example.repository.impl.CategoryRepository;
import org.example.repository.impl.PersonRepository;
import org.example.service.PersonService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean("entityManagerFactory")
    public SessionFactory getSessionFactory(){
        return HibernateUtil.getSessionFactory();
    }

    @Bean("personRepo")
    public PersonRepository getPersonRepository(){
        return new PersonRepository();
    }

    @Bean("categoryRepo")
    public CategoryRepository getCategoryRepository(){
        return new CategoryRepository();
    }

    @Bean("personService")
    public PersonService getPersonService(){
        return new PersonService();
    }

}
