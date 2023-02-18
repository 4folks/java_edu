package org.example.db;

import org.h2.tools.RunScript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static DbConnection INSTANCE;
    static final String DATABASE_URL = "jdbc:h2:./test_db";
    private Connection connection;

    private DbConnection() throws SQLException {
        Statement statement = null;
        try {

            Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(DATABASE_URL);
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS groups (id INT PRIMARY KEY auto_increment, name VARCHAR(255))");
            statement.execute("CREATE TABLE IF NOT EXISTS category (id INT PRIMARY KEY auto_increment, name VARCHAR(255))");
            statement.execute("CREATE TABLE IF NOT EXISTS goods (id INT PRIMARY KEY auto_increment, name VARCHAR(255), cost INT, category_id INT)");
            statement.execute("CREATE TABLE IF NOT EXISTS purchase (id INT PRIMARY KEY auto_increment, label VARCHAR(255), person_id INT, goods_id INT, item_count INT, p_timestamp DATE)");
            statement.execute("CREATE TABLE IF NOT EXISTS person (id INT PRIMARY KEY auto_increment, name VARCHAR(255), monthly_budget INTEGER, balance INTEGER, groups_id INT)");

            RunScript.execute(connection, new FileReader("src/main/resources/db/sql-db-init.sql"));

        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } catch(ReflectiveOperationException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            statement.close();
        }
    }

    public static DbConnection getInstance() throws SQLException {
        if(INSTANCE == null) {
            INSTANCE = new DbConnection();
        }

        return INSTANCE;
    }

    public Connection getConnection(){
        return connection;
    }
}
