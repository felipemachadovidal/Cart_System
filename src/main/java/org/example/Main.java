package org.example;

import entities.Cart;
import entities.Product;
import entities.Storage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Cart cart = new Cart(new ArrayList<>(), storage);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Manage Storage");
            System.out.println("2. Manage Cart");
            System.out.println("Else: Exit");
            int c = scanner.nextInt();
            if (c != 1 && c !=2) break;

            switch (c) {
                case 1 -> {
                    System.out.println("\nStorage:");
                    System.out.println("1. Show Products");
                    System.out.println("2. Add Product");
                    System.out.println("3. Delete Product");
                    System.out.println("4. Update Quantity");
                    int sc = scanner.nextInt();

                    switch (sc) {
                        case 1 -> storage.showProducts();
                        case 2 -> {
                            System.out.println("Enter product details (ID, name, price, quantity):");
                            int id = scanner.nextInt();
                            String name = scanner.next();
                            double price = scanner.nextDouble();
                            int quantity = scanner.nextInt();
                            storage.create(new Product("Category", id, name, price, quantity));
                        }
                        case 3 -> {
                            System.out.println("Enter product ID to delete:");
                            storage.delete(scanner.nextInt());
                        }
                        case 4 -> {
                            System.out.println("Enter product ID and new quantity:");
                            int id = scanner.nextInt();
                            int quantity = scanner.nextInt();
                            Product product = storage.read(id);
                            if (product != null) {
                                product.setQuantity(quantity);
                                storage.update(product);
                            } else {
                                System.out.println("Product not found.");
                            }
                        }
                        default -> System.out.println("Invalid option.");
                    }
                }
                case 2 -> {
                    System.out.println("\nCart:");
                    System.out.println("1. Add Product to Cart");
                    System.out.println("2. View Cart");
                    System.out.println("3. Cancel Purchase");
                    System.out.println("4. Complete Purchase");
                    int cc = scanner.nextInt();

                    switch (cc) {
                        case 1 -> {
                            storage.showProducts();
                            System.out.println("Enter product ID to add:");
                            int id = scanner.nextInt();
                            System.out.println("Enter quantity:");
                            int quantity = scanner.nextInt();

                            Product product = storage.read(id);
                            if (product != null && product.getQuantity() >= quantity) {
                                product.setQuantity(quantity);
                                cart.create(product);
                                System.out.println("Product added to cart.");
                            } else {
                                System.out.println("Insufficient stock or product not found.");
                            }
                        }
                        case 2 -> cart.showCart();
                        case 3 -> cart.cancelPurchase();
                        case 4 -> cart.processPurchase();
                        default -> System.out.println("Invalid option.");
                    }
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}
