package com.ferdingler.infrastructure.database;

import com.ferdingler.domain.model.Product;
import com.ferdingler.domain.model.ProductFactory;
import com.ferdingler.domain.model.ProductRepository;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class InMemoryProductRepositoryTest {

    @Inject
    @Named("InMemory")
    ProductRepository inMemoryProductRepository;

    @Inject
    ProductFactory productFactory;

    @Test
    public void saveAddsProduct() {
        Product product = productFactory.buildNewProduct("My Product", "My Category", 10);
        inMemoryProductRepository.save(product);

        List<Product> products = inMemoryProductRepository.getAll();
        assertEquals(products.get(0), product);
    }

    @Test
    public void newRepositoryStartsEmpty() {
        ProductRepository inMemory = new InMemoryProductRepository();
        List<Product> products = inMemory.getAll();
        assertTrue(products.isEmpty());
    }

}