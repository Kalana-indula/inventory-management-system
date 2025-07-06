package org.inventory.app.controller;

import org.inventory.app.entity.Product;
import org.inventory.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productService.createProduct(product));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> productList=productService.getAllProducts();

            if(productList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
