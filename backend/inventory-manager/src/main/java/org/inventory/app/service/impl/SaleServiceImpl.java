package org.inventory.app.service.impl;

import org.inventory.app.dto.SaleDto;
import org.inventory.app.entity.Product;
import org.inventory.app.entity.Sale;
import org.inventory.app.repository.ProductRepository;
import org.inventory.app.repository.SaleRepository;
import org.inventory.app.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;
    private ProductRepository productRepository;

    @Autowired
    public SaleServiceImpl(
            SaleRepository saleRepository,
            ProductRepository productRepository
    ){
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Sale createSale(SaleDto saleDto) {

        //check if the product available
        Product existingProduct=productRepository.findById(saleDto.getProductId()).orElse(null);

        if(existingProduct==null){
            return null;
        }

        //create new sale
        Sale sale=new Sale();

        sale.setProduct(existingProduct);
        sale.setDate(LocalDate.now());
        sale.setQuantity(saleDto.getQuantity());
        sale.setUnitPrice(existingProduct.getPrice());
        sale.setTotalAmount((saleDto.getQuantity())*(existingProduct.getPrice()));

        //update product qty
        updateProductQuantity(existingProduct,saleDto.getQuantity());

        return saleRepository.save(sale);
    }

    //find all sales
    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    //update product quantity
    private void updateProductQuantity(Product product,int quantity) {
        int currentQty= product.getStockQuantity();

        //setting new quantity
        product.setStockQuantity(currentQty-quantity);
        productRepository.save(product);
    }
}
