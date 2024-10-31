package database;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
        private String URL;
        private String USER;
        private String PASSWORD;

        public DatabaseConnection() {
                Dotenv dotenv = Dotenv.load();
                this.URL = dotenv.get("DB_URL");
                this.USER = dotenv.get("DB_USER");
                this.PASSWORD = dotenv.get("DB_PASSWORD");

        }

        public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(URL, USER, PASSWORD);
        }
}
