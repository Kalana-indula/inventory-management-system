package org.inventory.app.controller;

import org.inventory.app.dto.SaleDto;
import org.inventory.app.entity.Sale;
import org.inventory.app.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SaleController {

    private SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/sales")
    public ResponseEntity<?> createSale(@RequestBody SaleDto saleDto) {
        try{
            Sale newSale=saleService.createSale(saleDto);

            if(newSale==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(newSale);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/sales")
    public ResponseEntity<?> getAllSales(){
        try {
            List<Sale> sales=saleService.getAllSales();

            if(sales.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No sales found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(sales);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
