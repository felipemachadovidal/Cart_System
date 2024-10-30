package org.example;

import entities.Cart;
import entities.Product;
import entities.Storage;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Cart cart = new Cart(new ArrayList<>(), storage);


        Product product2 = new Product("Electronics", 2, "Smartphone", 999.99, 10);
        Product product3 = new Product("Home Appliances", 3, "Blender", 49.99, 15);
        Product product4 = new Product("Clothing", 4, "T-Shirt", 19.99, 20);


        storage.create(product2);
        storage.create(product3);
        storage.create(product4);


        cart.create(new Product("Electronics", 2, "Smartphone", 999.99, 2));
        cart.create(new Product("Clothing", 4, "T-Shirt", 19.99, 5));


        cart.cancelPurchase();


        storage.showProducts();
    }
}
