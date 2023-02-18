package org.example.db.crud;

import org.example.db.DbConnection;
import org.example.model.member.Group;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository {
    private DbConnection db;

    public GroupRepository() {
        try {

            db = DbConnection.getInstance();
        }catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public void create(Group group){
        String sql;
        sql = "INSERT INTO groups (name) VALUES (?)";

        try(PreparedStatement prepStat = db.getConnection().prepareStatement(sql)) {

            prepStat.setString(1,group.getGroupName());
            prepStat.execute();

        } catch (SQLException e){
            System.out.println("Problems occurred. Entry not created");
            e.printStackTrace();
        }
    }
    public void update(Group group){

    }

    public void delete(Group group){
        String sql;
        sql = "DELETE FROM groups WHERE name = ?;";

        try(PreparedStatement prepStat = db.getConnection().prepareStatement(sql)) {

            prepStat.setString(1,group.getGroupName());
            if(prepStat.execute()){
                System.out.println("Successfully deleted");
                return;
            }
            System.out.println("Problems occurred. Entry not deleted");
        } catch (SQLException e){

        }
    }

    public Group get(Group group){
        String sql;
        if(group.getGroupName()!=null) {
            sql = String.format("SELECT * FROM groups WHERE name = \'%s\';", group.getGroupName());
        } else {
            sql = sql = String.format("SELECT * FROM groups WHERE id = %d;", group.getId());
        }
        List<Group> groupList = new ArrayList<>();
        try(Statement statement = db.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            int id = rs.getInt(1);
            String name = rs.getString(2);
            Group group1 = new Group(name);
            group1.setId(id);
            return group1;

        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Problems occurred. Entry not deleted");
        return new Group();
    }

    public List<Group> getAll(){
        String sql = "SELECT * FROM groups";

        List<Group> groupList = new ArrayList<>();
        try(Statement statement = db.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Group group1 = new Group(id,name);
                groupList.add(group1);
            }
            return groupList;

        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Problems occurred. Entry not deleted");
        return new ArrayList<>();
    }

}
