package org.example.db.crud;

import org.example.db.DbConnection;
import org.example.model.member.Group;
import org.example.model.products.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private DbConnection db;

    public CategoryRepository() {
        try {

            db = DbConnection.getInstance();
        }catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public void create(Category category){
        String sql;
        sql = "INSERT INTO category (name) VALUES (?)";

        try(PreparedStatement prepStat = db.getConnection().prepareStatement(sql)) {

            prepStat.setString(1,category.getName());
            prepStat.execute();

        } catch (SQLException e){
            System.out.println("Problems occurred. Entry not created");
            e.printStackTrace();
        }
    }
    public void update(Category category){

    }

    public void delete(Category category){
        String sql;
        sql = "DELETE FROM category WHERE name = ?;";

        try(PreparedStatement prepStat = db.getConnection().prepareStatement(sql)) {

            prepStat.setString(1,category.getName());
            if(prepStat.execute()){
                System.out.println("Successfully deleted");
                return;
            }
            System.out.println("Problems occurred. Entry not deleted");
        } catch (SQLException e){

        }
    }

    public Category get(Category category){
        String sql;
        if(category.getName()!=null) {
            sql = String.format("SELECT * FROM category WHERE name = \'%s\';", category.getName());
        } else {
            sql = sql = String.format("SELECT * FROM category WHERE id = %d;", category.getId());
        }
        try(Statement statement = db.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            int id = rs.getInt(1);
            String name = rs.getString(2);
            Category category1 = new Category(id, name);
            return category1;

        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Problems occurred. Entry not deleted");
        return new Category();
    }
}
