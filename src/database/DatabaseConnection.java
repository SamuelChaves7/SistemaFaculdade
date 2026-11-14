
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConnection {

    private static Connection connection = null;

    private static final String HOST = "jdbc:mysql://localhost/";
    private static final String DATABASE = "faculdade";

    private static String getConfig(String key) {
        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream("database.properties")) {
            properties.load(input);
            return properties.getProperty(key);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar database.properties", e);
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                String user = getConfig("db.user");
                String password = getConfig("db.password");

                connection = DriverManager.getConnection(
                        HOST + DATABASE,
                        user,
                        password
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
