package entities;

import Services.Operations;

import java.util.List;

public class Cart implements Operations {

    private List<Product>  list;

    public Cart(List<Product> list) {
        this.list = list;
    }

    @Override
    public void create(Object item) {

    }

    @Override
    public Object read(int id) {
        return null;
    }

    @Override
    public void update(Object item) {

    }

    @Override
    public void delete(int id) {

    }

}
