package entities;

import Services.Operations;
import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Cart implements Operations<Product> {

    private List<Product> list;
    private Storage storage;
    private final DatabaseConnection dbConnection;

    public Cart(List<Product> list, Storage storage) {
        this.list = list;
        this.storage = storage;
        this.dbConnection = new DatabaseConnection();
    }

    @Override
    public void create(Product product) {

        Product productFromStorage = storage.read(product.getId());
        if (productFromStorage != null && productFromStorage.getQuantity() >= product.getQuantity()) {
            productFromStorage.setQuantity(productFromStorage.getQuantity() - product.getQuantity());
            storage.update(productFromStorage);
            list.add(product);

            String sql = "INSERT INTO shopping_cart (id, name, category, price, quantity) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = dbConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, product.getId());
                stmt.setString(2, product.getName());
                stmt.setString(3, product.getCategory());
                stmt.setDouble(4, product.getPrice());
                stmt.setInt(5, product.getQuantity());
                stmt.executeUpdate();
                System.out.println("Product added to cart and database: " + product.getName());
            } catch (SQLException e) {
                System.err.println("Error adding product to shopping_cart table: " + e.getMessage());
            }


        } else {
            System.out.println("Insufficient stock for product: " + product.getName());
        }
    }

    @Override
    public Product read(int id) {

        return list.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Product product) {

        Product existingProduct = read(product.getId());
        if (existingProduct != null) {
            int difference = product.getQuantity() - existingProduct.getQuantity();
            Product productFromStorage = storage.read(product.getId());
            productFromStorage.setQuantity(productFromStorage.getQuantity() - difference);
            storage.update(productFromStorage);
            existingProduct.setQuantity(product.getQuantity());

            System.out.println("Product updated in cart: " + product.getName());
        } else {
            System.out.println("Product not found in cart: " + product.getName());
        }
    }

    @Override
    public void delete(int id) {

        Product productToRemove = read(id);
        if (productToRemove != null) {
            Product productFromStorage = storage.read(id);
            productFromStorage.setQuantity(productFromStorage.getQuantity() + productToRemove.getQuantity());
            storage.update(productFromStorage);

            list.remove(productToRemove);
            String sql = "DELETE FROM shopping_cart WHERE id = ?";
            try (Connection conn = dbConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, productToRemove.getId());
                stmt.executeUpdate();
                System.out.println("Product removed from cart and database: " + productToRemove.getName());
            } catch (SQLException e) {
                System.err.println("Error removing product from shopping_cart: " + e.getMessage());
            }
        } else {
            System.out.println("Product not found in cart to remove.");
        }
    }

    public void cancelPurchase() {

        for (Product product : list) {
            Product productFromStorage = storage.read(product.getId());

            if (productFromStorage != null) {
                productFromStorage.setQuantity(productFromStorage.getQuantity() + product.getQuantity());
                storage.update(productFromStorage);
            } else {
                System.err.println("Warning: Product not found in storage for ID: " + product.getId());
            }
        }

        list.clear();

        String sql = "DELETE FROM shopping_cart";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
            System.out.println("Cart has been canceled. All products returned to storage.");
        } catch (SQLException e) {
            System.err.println("Error clearing shopping_cart table: " + e.getMessage());
        }
    }


    public void processPurchase() {

        showCart();


        list.clear();

        String sql = "DELETE FROM shopping_cart";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
            System.out.println("Purchase processed. Cart and database cart table cleared.");
        } catch (SQLException e) {
            System.err.println("Error clearing shopping_cart table after purchase: " + e.getMessage());
        }
    }

    public void showCart() {

        if (list.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Products in cart:");
            double total = 0.0;
            for (Product product : list) {
                System.out.printf("ID: %d, Name: %s, Quantity: %d, Price: %.2f%n",
                        product.getId(), product.getName(), product.getQuantity(), product.getPrice());
                total += product.getTotalValue();
            }
            System.out.printf("Final price: %.2f",total);

        }
    }
}
