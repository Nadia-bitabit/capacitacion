package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseManager {

    public static Connection connect() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "v;J48+rXxn");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al conectar con la base de datos");
        }
    }
    public static void disconnect(Connection connection) throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) connection.close();
    }
}
