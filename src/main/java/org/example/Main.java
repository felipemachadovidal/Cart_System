package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import database.DatabaseConnection;
import entities.Cart;
import entities.Product;
import entities.Storage;

public class Main {
    public static void main(String[] args) {

        Storage storage = new Storage();

        Product novoProduto = new Product("Smartphone", 1, "iPhone", 999.99, 10);
        storage.create(novoProduto);

        List<Product> cartList = new ArrayList<>();
        Cart cart = new Cart(cartList, storage);

        System.out.println("\n--- Test> Add in the Cart ---");
        Product produtoCarrinho = new Product("Smartphone", 1, "iPhone", 999.99, 2);
        cart.create(produtoCarrinho);

        Product produtoStorage = storage.read(1);
        System.out.println("Storage quantity left : " + produtoStorage.getQuantity());

        System.out.println("\n--- Test: Update  ---");
        produtoCarrinho.setQuantity(5);
        cart.update(produtoCarrinho);

        produtoStorage = storage.read(1);
        System.out.println("Quantity left:: " + produtoStorage.getQuantity());

        System.out.println("\n--- Test: Removing item on cart ---");
        cart.delete(1);

        produtoStorage = storage.read(1);
        System.out.println("Quantity left: : " + produtoStorage.getQuantity());
}}
