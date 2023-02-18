package org.example.db.crud;

import org.example.db.DbConnection;
import org.example.model.products.Category;
import org.example.model.products.Goods;

import java.sql.*;

public class GoodsRepository {
    private DbConnection db;
    private final CategoryRepository categoryRepository = new CategoryRepository();

    public GoodsRepository() {
        try {

            db = DbConnection.getInstance();
        }catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public void create(Goods goods){
        String sql;
        sql = "INSERT INTO goods (name, cost, category, g_timestamp) VALUES (?,?,?)";

        try(PreparedStatement prepStat = db.getConnection().prepareStatement(sql)) {

            prepStat.setString(1,goods.getProductName());
            prepStat.setLong(2,goods.getCost());

            Category category = categoryRepository.get(goods.getCategory());
            if(category==null){
                categoryRepository.create(goods.getCategory());
            }
            category = categoryRepository.get(goods.getCategory());

            prepStat.setInt(3,category.getId());

            prepStat.execute();

        } catch (SQLException e){
            System.out.println("Problems occurred. Entry not created");
            e.printStackTrace();
        }
    }
    public void update(Category category){

    }

    public void delete(Goods goods){
        String sql;
        sql = "DELETE FROM goods WHERE name = ?;";

        try(PreparedStatement prepStat = db.getConnection().prepareStatement(sql)) {

            prepStat.setString(1, goods.getProductName());
            if(prepStat.execute()){
                System.out.println("Successfully deleted");
                return;
            }
            System.out.println("Problems occurred. Entry not deleted");
        } catch (SQLException e){

        }
    }

    public Goods get(Goods goods){
        String sql;
        if(goods.getProductName()!=null) {
            sql = String.format("SELECT * FROM goods WHERE name = \'%s\';", goods.getProductName());
        } else {
            sql = sql = String.format("SELECT * FROM goods WHERE id = %d;", goods.getId());
        }
        try(Statement statement = db.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            int id = rs.getInt(1);
            String name = rs.getString(2);
            long cost = rs.getLong(3);
            int categoryId = rs.getInt(4);

            Category category = categoryRepository.get(new Category(categoryId));

            Goods result = new Goods(name, category);
            result.setId(id);
            result.setCost(cost);

            return result;

        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Problems occurred. Entry not deleted");
        return new Goods();
    }
}
