package org.example.db.crud;

import org.example.db.DbConnection;
import org.example.model.member.Group;
import org.example.model.member.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PersonRepository {
    private DbConnection db;
    private final GroupRepository groupService = new GroupRepository();

    public PersonRepository() {
        try {

            db = DbConnection.getInstance();
        }catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public void create(Person person){
        String sql;
        sql = "INSERT INTO person(name, monthly_budget, balance, groups_id) VALUES (?,?,?,?)";

        try(PreparedStatement prStmt = db.getConnection().prepareStatement(sql)) {

            Iterator<Group> it = person.getGroups().iterator();
            while(it.hasNext()){
                prStmt.setString(1,person.getName());
                prStmt.setLong(2,person.getMonthlyBudget());
                prStmt.setLong(3,person.getBalance());
                Group group = groupService.get(it.next());
                prStmt.setInt(4,group.getId());
                prStmt.addBatch();

            }
            int [] numUpdates=prStmt.executeBatch();
            for (int i=0; i < numUpdates.length; i++) {
                if (numUpdates[i] == -2)
                    System.out.println("Execution " + i +
                            ": unknown number of rows updated");
                else
                    System.out.println("Execution " + i +
                            "successful: " + numUpdates[i] + " rows updated");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(Person person){
        String sql;
        sql = "UPDATE person SET monthly_budget = ?, balance = ? WHERE  name = ?;";

        try(PreparedStatement prStmt = db.getConnection().prepareStatement(sql)) {
            prStmt.setLong(1, person.getMonthlyBudget());
            prStmt.setLong(2, person.getBalance());
            prStmt.setString(3,person.getName());
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Person person){

    }

    public Person get(Person person){
        String sql;
        if(person.getName()!=null) {
            sql = String.format("SELECT * FROM person p " +
                    "JOIN groups g on(p.groups_id=g.id) " +
                    "WHERE p.name = \'%s\';", person.getName());
        }else {
            sql = String.format("SELECT * FROM person WHERE id = %d;", person.getId());
        }
        List<Person> personList = new ArrayList<>();
        try(Statement statement = db.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            rs.next();

            int id = rs.getInt(1);
            String name = rs.getString(2);
            Long monthlyBudget = rs.getLong(3);
            int groupsId = rs.getInt(5);
            String groupsName = rs.getString(6);

            Person dbPerson = new Person(name);
            dbPerson.setId(id);
            dbPerson.setMonthlyBudget(monthlyBudget);
            List<Group> groups= new ArrayList<>();

            Group group1 = new Group(groupsId, groupsName);
            groups.add(group1);
            while(rs.next()){
                groupsId = rs.getInt(5);
                groupsName = rs.getString(6);
                group1 = new Group(groupsId, groupsName);
                groups.add(group1);
            }

            dbPerson.setGroups(groups);
            return dbPerson;
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Problems occurred. Entry not deleted");
        return new Person();
    }

    public List<Person> getAll(){
        String sql = "SELECT * FROM person p " +
                "JOIN groups g on(p.groups_id=g.id)" +
                "group by p.name, p.id order by p.name";
        List<Person> personList = new ArrayList<>();
        try(Statement statement = db.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            String checkName = null;
            Person dbPerson = new Person();
            List<Group> groups = new ArrayList<>();
            int id = -1;
            long monthlyBudget = -1;
            while(rs.next()) {
                id = rs.getInt(1);
                String name = rs.getString(2);
                monthlyBudget = rs.getLong(3);
                int groupsId = rs.getInt(5);
                String groupsName = rs.getString(6);

                if(checkName==null){
                    checkName=name;
                }
                if(checkName.equals(name)) {
                    Group group1 = new Group(groupsId, groupsName);
                    groups.add(group1);
                    dbPerson.setId(id);
                    dbPerson.setMonthlyBudget(monthlyBudget);
                    dbPerson.setGroups(groups);
                    continue;
                } else {
                    personList.add(dbPerson);
                    dbPerson = new Person(name);
                    groups = new ArrayList<>();
                }

            }
            dbPerson.setId(id);
            dbPerson.setMonthlyBudget(monthlyBudget);
            dbPerson.setGroups(groups);
            personList.add(dbPerson);

            return personList;
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Problems occurred. Entry not deleted");
        return new ArrayList<>();
    }

}
