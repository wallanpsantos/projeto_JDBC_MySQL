package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbConnection {

    private static Connection connection = null;

    public static Connection getConnection() throws IOException, SQLException {
        if (connection == null) {
            Properties properties = carregarPropriedades();
            connection = DriverManager.getConnection(properties.getProperty("dburl"), properties.getProperty("user"), properties.getProperty("password"));
        }
        return connection;
    }

    private static Properties carregarPropriedades() throws IOException {
        File file;
        try (FileInputStream fileInputStream = new FileInputStream("src/db.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    public static void getCloseConnection(Connection connection) throws SQLException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void getCloseStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void getCloseResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

}

