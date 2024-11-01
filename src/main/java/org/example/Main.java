package org.example;

import entities.Cart;
import entities.Product;
import entities.Storage;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Cart cart = new Cart(new ArrayList<>(), storage);
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Manage Storage");
            System.out.println("2. Manage Cart");
            System.out.println("Else: Exit");
            int c = scn.nextInt();
            if (c != 1 && c != 2) break;

            switch (c) {
                case 1 -> {
                    boolean condition = true;
                    while (condition) {
                        System.out.println("Storage Menu: ");
                        scn.next();
                        System.out.println("1. Show Products");
                        System.out.println("2. Add Product");
                        System.out.println("3. Delete Product");
                        System.out.println("4. Update Quantity");
                        System.out.println("Else: Return to main menu");
                        int sc = scn.nextInt();

                        switch (sc) {
                            case 1 -> storage.showProducts();
                            case 2 -> {
                                System.out.println("Enter product details (ID, name, price, quantity):");
                                int id = scn.nextInt();
                                String name = scn.next();
                                double price = scn.nextDouble();
                                int quantity = scn.nextInt();
                                storage.create(new Product("Category", id, name, price, quantity));
                            }
                            case 3 -> {
                                System.out.println("Enter product ID to delete:");
                                storage.delete(scn.nextInt());
                            }
                            case 4 -> {
                                System.out.println("Enter product ID and new quantity:");
                                int id = scn.nextInt();
                                int quantity = scn.nextInt();
                                Product product = storage.read(id);
                                if (product != null) {
                                    product.setQuantity(quantity);
                                    storage.update(product);
                                } else {
                                    System.out.println("Product not found.");
                                }
                            }
                            default -> {
                                System.out.println("Returning to main menu.");
                                condition = false;
                            }
                        }
                    }
                }
                case 2 -> {
                    boolean condition = true;
                    while (condition) {
                        System.out.println("Cart Menu: ");
                        System.out.println("1. Add Product to Cart");
                        System.out.println("2. View Cart");
                        System.out.println("3. Remove Product from Cart");
                        System.out.println("4. Cancel Purchase");
                        System.out.println("5. Complete Purchase");
                        System.out.println("Else: Return to main menu");
                        int cc = scn.nextInt();

                        switch (cc) {
                            case 1 -> {
                                storage.showProducts();
                                System.out.println("Enter product ID to add:");
                                int id = scn.nextInt();
                                System.out.println("Enter quantity:");
                                int quantity = scn.nextInt();

                                Product product = storage.read(id);
                                if (product != null && product.getQuantity() >= quantity) {
                                    product.setQuantity(quantity);
                                    cart.create(product);
                                    System.out.println("Product added to cart.");
                                    cart.showCart();
                                } else {
                                    System.out.println("Insufficient stock or product not found.");
                                }
                            }
                            case 2 -> cart.showCart();
                            case 3 -> {
                                System.out.println("Enter product ID to remove:");
                                int id = scn.nextInt();
                                cart.delete(id);
                                System.out.println("Product removed from cart.");
                                cart.showCart();
                            }
                            case 4 -> {
                                cart.cancelPurchase();
                                condition = false;
                                System.out.println("Purchase canceled, returning to main menu.");
                            }
                            case 5 -> {
                                System.out.println("Your Cart:");
                                scn.next();
                                cart.showCart();
                                cart.processPurchase();
                                condition = false;
                                System.out.println("Purchase completed, returning to main menu.");
                            }
                            default -> {
                                System.out.println("Returning to main menu.");
                                condition = false;
                            }
                        }
                    }
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        scn.close();
    }
}
