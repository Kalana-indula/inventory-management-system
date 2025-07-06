package org.inventory.app.service;

import org.inventory.app.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product createProduct(Product product);

    List<Product> getAllProducts();
}
