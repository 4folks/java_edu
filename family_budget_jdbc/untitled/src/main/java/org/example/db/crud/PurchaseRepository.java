package org.example.db.crud;

import org.example.db.DbConnection;
import org.example.model.Purchase;
import org.example.model.member.Group;
import org.example.model.member.Person;
import org.example.model.products.Goods;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PurchaseRepository {

    private DbConnection db;

    private final GoodsRepository goodsRepository = new GoodsRepository();

    public PurchaseRepository() {
        try {

            db = DbConnection.getInstance();
        }catch(SQLException e) {
            e.printStackTrace();
        }

    }

    public void create(Purchase purchase){
        String sql;
        sql = "INSERT INTO purchase (label, person_id, goods_id, item_count, p_timestamp) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement prepStat = db.getConnection().prepareStatement(sql)) {
            Iterator<Map.Entry<Goods,Integer>> it = purchase.getGoodsAndCount().entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<Goods, Integer> nextEntry = it.next();
                prepStat.setString(1,purchase.getLabel());
                prepStat.setInt(2,purchase.getPersonId());
                prepStat.setInt(3,nextEntry.getKey().getId());
                prepStat.setInt(4,nextEntry.getValue());
                Date sqlTimestamp = new Date(purchase.getTimestamp().getTime());
                prepStat.setDate(5, sqlTimestamp);

                prepStat.addBatch();

            }
            int [] numUpdates=prepStat.executeBatch();
            for (int i=0; i < numUpdates.length; i++) {
                if (numUpdates[i] == -2)
                    System.out.println("Execution " + i +
                            ": unknown number of rows updated");
                else
                    System.out.println("Execution " + i +
                            "successful: " + numUpdates[i] + " rows updated");
            }
        } catch (SQLException e){
            System.out.println("Problems occurred. Entry not created");
            e.printStackTrace();
        }
    }
    public void update(Group group){

    }

    public void delete(Purchase purchase){
        String sql;
        sql = "DELETE FROM purchase WHERE label = ? AND person_id = ?;";

        try(PreparedStatement prepStat = db.getConnection().prepareStatement(sql)) {

            prepStat.setString(1,purchase.getLabel());
            prepStat.setInt(2,purchase.getPersonId());
            if(prepStat.execute()){
                System.out.println("Successfully deleted");
                return;
            }
            System.out.println("Problems occurred. Entry not deleted");
        } catch (SQLException e){

        }
    }

    public Purchase get(Purchase purchase){
        String sql;
        if(purchase.getLabel()!=null) {
            sql = String.format("SELECT * FROM purchase WHERE label = '%s' AND person_id=%d;", purchase.getLabel(), purchase.getPersonId());
        } else {
            sql = sql = "SELECT * FROM purchase WHERE p_timestamp LIKE %"
                    + new java.sql.Date(purchase.getTimestamp().getTime())
                    + "% AND person_id=" + purchase.getPersonId() + ";";
        }
        try(Statement statement = db.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            Map<Goods, Integer> goodsAndCount = new HashMap<>();
            int id =-1;
            String label = null;
            int person_id = -1;
            int goods_id;
            int count;
            Date sqlTimestamp = null;

            while(rs.next()) {
                id = rs.getInt(1);
                label = rs.getString(2);
                person_id = rs.getInt(3);
                goods_id = rs.getInt(4);
                count = rs.getInt(5);
                sqlTimestamp = rs.getDate(6);

                Goods goods = goodsRepository.get(new Goods(goods_id));

                goodsAndCount.put(goods,count);
            }
            java.util.Date date = new java.util.Date(sqlTimestamp.getTime());
            Purchase purchase1 = new Purchase(id, person_id, date);
            purchase1.setLabel(label);
            purchase1.setGoodsAndCount(goodsAndCount);
            return purchase1;

        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Problems occurred. Entry not deleted");
        return new Purchase();
    }

    public List<Purchase> getAll(Person person){
        String sql;
        sql = "SELECT * FROM purchase WHERE person_id=? group by label, p_timestamp, id order by p_timestamp";
        try(PreparedStatement prStmt = db.getConnection().prepareStatement(sql)) {
            prStmt.setInt(1,person.getId());
            ResultSet rs = prStmt.executeQuery();
            List<Purchase> purchaseList = new ArrayList<>();
            Map<Goods, Integer> goodsAndCount = new HashMap<>();

            int id =-1;
            String label = null;
            int person_id = -1;
            int goods_id;
            int count;
            Date sqlTimestamp = null;
            String checkLabel = null;
            Purchase purchase1 = null;

            while(rs.next()) {
                id = rs.getInt(1);
                label = rs.getString(2);
                person_id = rs.getInt(3);
                goods_id = rs.getInt(4);
                count = rs.getInt(5);
                sqlTimestamp = rs.getDate(6);

                Goods goods = goodsRepository.get(new Goods(goods_id));

                if((checkLabel == null) || !checkLabel.equals(label)){
                    if(checkLabel == null){
                        checkLabel = label;
                        continue;
                    }

                    java.util.Date date = new java.util.Date(sqlTimestamp.getTime());
                    purchase1 = new Purchase(id, person_id, date);
                    purchase1.setLabel(checkLabel);

                    checkLabel = label;
                    purchase1.setGoodsAndCount(goodsAndCount);
                    purchaseList.add(purchase1);
                    goodsAndCount = new HashMap<>();
                }

                goodsAndCount.put(goods,count);
            }

//            java.util.Date date = new java.util.Date(sqlTimestamp.getTime());
            purchase1 = new Purchase(checkLabel, person_id);
//            purchase1.setLabel(checkLabel);
            purchase1.setGoodsAndCount(goodsAndCount);
            purchaseList.add(purchase1);


            return purchaseList;

        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Problems occurred. Entry not deleted");
        return new ArrayList<>();
    }
}
