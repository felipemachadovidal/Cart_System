package entities;

import java.sql.Connection;

public class Product {

    private int id;
    private String name;
    private String category;
    private double price;
    private int quantity;

    public Product(String category, int id, String name, double price, int quantity) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public String getCategory() {
        return category;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalValue() {
        return price * quantity;
    }


    @Override
    public String toString() {
        return "Product{" +
                "category='" + category + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
