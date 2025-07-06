package org.inventory.app.controller;

import org.inventory.app.dto.PurchaseDto;
import org.inventory.app.entity.Purchase;
import org.inventory.app.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PurchaseController {

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    //create a purchase
    @PostMapping("/purchases")
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseDto purchaseDto) {

        try{
            Purchase newPurchase = purchaseService.createPurchase(purchaseDto);

            if(newPurchase==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existing product found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(newPurchase);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/purchases")
    public ResponseEntity<?> getAllPurchases() {
        try{

            List<Purchase> purchases = purchaseService.getAllPurchases();

            if(purchases.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No purchases found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(purchases);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
