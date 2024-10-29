package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import database.DatabaseConnection;
import entities.Product;
import entities.Storage;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();

        Product novoProduto = new Product("Smartphone", 1, "Iphone", 999.99, 10);
        Product p = null;

        System.out.println(storage.read(1));
        storage.delete(1);
        System.out.println(storage.read(1));

        System.out.println(storage.read(1));
}}
