package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


        private String URL = "jdbc:mysql://localhost:3306/db_cart";
        private String USER = "root";
        private String PASSWORD = "rootrei77";


        public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(URL, USER, PASSWORD);
        }

        public DatabaseConnection() {
        }

        public String getPASSWORD() {
                return PASSWORD;
        }

        public void setPASSWORD(String PASSWORD) {
                this.PASSWORD = PASSWORD;
        }

        public String getURL() {
                return URL;
        }

        public void setURL(String URL) {
                this.URL = URL;
        }

        public String getUSER() {
                return USER;
        }

        public void setUSER(String USER) {
                this.USER = USER;
        }
}
