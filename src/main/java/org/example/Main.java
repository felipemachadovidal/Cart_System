package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import database.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        try {
            dbConnection.getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
