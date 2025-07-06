package org.inventory.app.service.impl;

import org.inventory.app.dto.PurchaseDto;
import org.inventory.app.entity.Product;
import org.inventory.app.entity.Purchase;
import org.inventory.app.repository.ProductRepository;
import org.inventory.app.repository.PurchaseRepository;
import org.inventory.app.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepository;

    private ProductRepository productRepository;

    @Autowired
    public PurchaseServiceImpl(
            PurchaseRepository purchaseRepository,
            ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Purchase createPurchase(PurchaseDto purchaseDto) {

        //check if the product available
        Product existingProduct=productRepository.findById(purchaseDto.getProductId()).orElse(null);

        if(existingProduct==null){
            return null;
        }

        //create new purchase
        Purchase purchase=new Purchase();

        purchase.setProduct(existingProduct);
        purchase.setDate(LocalDate.now());
        purchase.setQuantity(purchaseDto.getQuantity());

        //this is need to be updated
        purchase.setUnitCost(existingProduct.getPrice());
        purchase.setTotalCost((purchaseDto.getQuantity())*(existingProduct.getPrice()));

        //update product qty
        updateProductQuantity(existingProduct,purchaseDto.getQuantity());

        return purchaseRepository.save(purchase);

    }

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    //update product quantity
    private void updateProductQuantity(Product product,int quantity) {
        int currentQty= product.getStockQuantity();

        //setting new quantity
        product.setStockQuantity(currentQty+quantity);
        productRepository.save(product);
    }
}
