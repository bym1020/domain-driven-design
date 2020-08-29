package com.ferdingler.application;

import com.ferdingler.api.CreateProductRequest;
import com.ferdingler.domain.model.ProductRepository;
import com.ferdingler.infrastructure.database.InMemoryProductRepository;
import io.micronaut.context.annotation.Property;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import javax.inject.Inject;

@MicronautTest
@Property(name = "flags.productRepository", value = "InMemory")
class CatalogApplicationTest {

    @MockBean(InMemoryProductRepository.class)
    ProductRepository productRepository() {
        return mock(ProductRepository.class);
    }

    @Inject
    CatalogApplication catalogApplication;

    @Inject
    ProductRepository productRepository;

    @Test
    public void createProductCallsSaveMethodOnRepository() {
        CreateProductRequest request = new CreateProductRequest();
        request.setProductName("Banana");
        request.setCategoryName("Books");

        catalogApplication.createProduct(request);

        verify(productRepository).save(any());
    }

    @Test
    public void listProductsCallsGetAllMethodOnRepository() {
        catalogApplication.listProducts();
        verify(productRepository).getAll();
    }

}