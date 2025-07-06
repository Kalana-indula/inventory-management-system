package org.inventory.app.service.impl;

import org.inventory.app.entity.Product;
import org.inventory.app.repository.ProductRepository;
import org.inventory.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        //create a new product
        Product newProduct = new Product();

        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setRating(product.getRating());
        newProduct.setStockQuantity(product.getStockQuantity());
        return productRepository.save(newProduct);
    }

    //get all available products
    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }
}
