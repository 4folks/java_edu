package org.example.repository;

import org.example.db_config.HibernateUtil;
import org.example.model.Purchase;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GeneralCrud<T> implements Serializable {

    @Autowired
    private SessionFactory sessionFactory;
    private Class cl;

    public GeneralCrud(Class cl) {
        this.cl = cl;
    }


    public void create(T item) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(item);

            session.getTransaction().commit();
            session.close();
        }catch (HibernateException | ClassCastException e){
            e.printStackTrace();
        }

    }



    public void update(T item) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();

            session.close();
        }catch (HibernateException | ClassCastException e ){
            e.printStackTrace();
        }
    }



    public Object get(String label) {
        return null;
    }


    public void remove(T item) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.remove(item);
            session.getTransaction().commit();
            session.close();
        }catch (HibernateException | ClassCastException e ){
            e.printStackTrace();
        }
    }




    public T get(int id) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            T obj = (T) session.get(cl, id);
            session.getTransaction().commit();
            session.close();
            return obj;
        }catch (HibernateException | ClassCastException e){
            e.printStackTrace();
        }
        return null;
    }


    public List<T> getAll() {
        try{
            String sql = String.format("Select o FROM %s o", cl.getSimpleName());
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<T> all = session.createQuery(sql, cl).list();
            session.getTransaction().commit();
            session.close();
            return all;
        }catch (HibernateException | ClassCastException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
