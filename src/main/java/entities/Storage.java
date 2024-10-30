package entities;

import Services.Operations;
import database.DatabaseConnection;
import entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Storage implements Operations<Product> {
    private final DatabaseConnection dbConnection;

    public Storage() {
        this.dbConnection = new DatabaseConnection();
    }

    @Override
    public void create(Product product) {
        String sql = "INSERT INTO storage (id, name, category, price, quantity) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getCategory());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding product in the Storage: " + e.getMessage());
        }
    }

    @Override
    public void delete(int productId) {
        String sql = "DELETE FROM storage WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting product in storage: " + e.getMessage());
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE storage SET quantity = ? WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, product.getQuantity());
            stmt.setInt(2, product.getId());
            stmt.executeUpdate();
            System.out.println("Updated product quantity in storage for ID: " + product.getId());
        } catch (SQLException e) {
            System.err.println("Error updating product in storage: " + e.getMessage());
        }
    }

    @Override
    public Product read(int productId) {
        String sql = "SELECT * FROM storage WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getString("category"),
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error in search: " + e.getMessage());
        }
        return null;
    }

    public void showProducts() {
        String sql = "SELECT * FROM storage";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Products in Storage:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                System.out.println("ID: " + id + ", Name: " + name + ", Category: " + category +
                        ", Price: $" + price + ", Quantity: " + quantity);
            }
        } catch (SQLException e) {
            System.err.println("Error displaying products in storage: " + e.getMessage());
        }
    }

}
