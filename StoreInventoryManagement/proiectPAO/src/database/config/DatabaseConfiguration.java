package database.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/StoreInventoryManagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static DatabaseConfiguration single_instance = null;

    private Connection databaseConnection;

    public static DatabaseConfiguration getDatabaseConfiguration() {
        if(single_instance == null)
            single_instance = new DatabaseConfiguration();
        return single_instance;
    }

    private DatabaseConfiguration() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            }
        catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Connection getDatabaseConnection() {
        return databaseConnection;
    }
}
