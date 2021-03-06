package com.ferdingler.infrastructure.database;

import com.ferdingler.domain.model.Product;
import com.ferdingler.domain.model.ProductRepository;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class InMemoryProductRepository implements ProductRepository {

    private final List<Product> products;

    public InMemoryProductRepository() {
        this.products = new ArrayList<>();
    }

    public List<Product> getAll() {
        return this.products;
    }

    public void save(Product product) {
        this.products.add(product);
    }

}
