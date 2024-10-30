package entities;

import Services.Operations;
import java.util.List;

public class Cart implements Operations<Product> {

    private List<Product> list;
    private Storage storage;

    public Cart(List<Product> list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    @Override
    public void create(Product product) {
        Product productFromStorage = storage.read(product.getId());
        if (productFromStorage != null && productFromStorage.getQuantity() >= product.getQuantity()) {
            productFromStorage.setQuantity(productFromStorage.getQuantity() - product.getQuantity());
            storage.update(productFromStorage);

            list.add(product);
            System.out.println("Product added to cart: " + product.getName());
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
            System.out.println("Product removed from cart: " + productToRemove.getName());
        } else {
            System.out.println("Product not found in cart to remove.");
        }
    }
}
