package database;

import FranchiseSystem.Franchise;
import FranchiseSystem.StockManagement;

import java.sql.*;
import java.util.ArrayList;

import static database.config.DatabaseConfiguration.getDatabaseConfiguration;

public class DbStockManagement {

    public void DbStockManagement() {
        Connection connection = getDatabaseConfiguration().getDatabaseConnection();
        createTable(connection);
    }

    private void createTable(Connection databaseConnection) {
        final String query = "CREATE TABLE IF NOT EXISTS StockManagement (\n" +
                "StockManagementsd INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "address VARCHAR(128) NOT NULL,\n" +
                "StockManagementBank DECIMAL NOT NULL,\n";
        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    private StockManagement mapToStockManagements(ResultSet resultSet) throws SQLException {
//        StockManagement StockManagements = new StockManagement(resultSet.getString(0));
//        return StockManagements;
//    }
//
//    public void findAll(){
//        ArrayList<StockManagement> StockManagements  = new ArrayList<>();
//        final String query = "SELECT * FROM StockManagements";
//        try{
//            Statement statement = getDatabaseConfiguration().getDatabaseConnection().createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()) {
//                StockManagements.add(mapToStockManagements(resultSet));
//            }
//        } catch (SQLException exception) {
//            throw new RuntimeException("Something went wrong: ");
//        }
//    }
//
//    public void save(StockManagement StockManagement) {
//        final String query = "INSERT INTO StockManagement(id,address) values(?,?,?,?)";
//        try {
//            PreparedStatement preparedStatement = getDatabaseConfiguration()
//                    .getDatabaseConnection().prepareStatement(query, Statement.NO_GENERATED_KEYS);
//            preparedStatement.setString(2, StockManagement.getAddress());
//            preparedStatement.setBigDecimal(3, StockManagement.getStockManagementBank());
//            preparedStatement.execute();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

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
            throw new RuntimeException("Something went wrong while tying to updated the StockManagement with id: " + id);
        }
    }
}
