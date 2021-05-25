package database;

import FranchiseSystem.Franchise;
import FranchiseSystem.Store;
import database.config.DatabaseConfiguration;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

import static database.config.DatabaseConfiguration.getDatabaseConfiguration;

public class DbStore {

    public DbStore() {
        Connection connection = getDatabaseConfiguration().getDatabaseConnection();
        createTable(connection);
    }

    private void createTable(Connection databaseConnection) {
        final String query = "CREATE TABLE IF NOT EXISTS Store (\n" +
                "Storesd INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "address VARCHAR(128) NOT NULL,\n" +
                "storeBank DECIMAL NOT NULL,\n";
        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Store mapToStores(ResultSet resultSet) throws SQLException {
        Store Stores = new Store(resultSet.getString(0));
        return Stores;
    }

    public void findAll(){
        ArrayList<Store> Stores  = new ArrayList<>();
        final String query = "SELECT * FROM Stores";
        try{
            Statement statement = getDatabaseConfiguration().getDatabaseConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Stores.add(mapToStores(resultSet));
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong: ");
        }
    }

    public void save(Store store) {
        final String query = "INSERT INTO Store(id,address) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDatabaseConfiguration()
                    .getDatabaseConnection().prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setString(2, store.getAddress());
            preparedStatement.setBigDecimal(3, store.getStoreBank());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //CallableStatement
    public boolean update(int id) {
        try (Connection connection = getDatabaseConfiguration().getDatabaseConnection()) {
            String query = "{?= call borrow(?)}";

            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(2, id);
            callableStatement.registerOutParameter(1, Types.INTEGER);

            callableStatement.executeUpdate();
            int response = callableStatement.getByte(1);

            return response == 1;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to updated the store with id: " + id);
        }
    }

}
