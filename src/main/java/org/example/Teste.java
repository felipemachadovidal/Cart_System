package org.example;

import database.DatabaseConnection;
import entities.Storage;

public class Teste {
    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();

        try {
            dbConnection.getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
        Storage storage = new Storage();
        storage.showProducts();

    }
}
