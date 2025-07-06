package org.inventory.app.controller;

import org.inventory.app.entity.PurchaseSummary;
import org.inventory.app.service.PurchaseSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PurchaseSummaryController {

    private PurchaseSummaryService purchaseSummaryService;

    @Autowired
    public PurchaseSummaryController(PurchaseSummaryService purchaseSummaryService) {
        this.purchaseSummaryService = purchaseSummaryService;
    }

    @PostMapping("/purchase-summaries")
    public ResponseEntity<?> createPurchaseSummary(@RequestBody PurchaseSummary purchaseSummary) {
        try{
            PurchaseSummary createdPurchaseSummary = purchaseSummaryService.createPurchaseSummary(purchaseSummary);

            return ResponseEntity.status(HttpStatus.OK).body(createdPurchaseSummary);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/purchase-summaries")
    public ResponseEntity<?> getAllPurchaseSummaries() {
        try{
            List<PurchaseSummary> purchaseSummaries = purchaseSummaryService.getAllPurchaseSummaries();

            if(purchaseSummaries.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No purchase summaries found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(purchaseSummaries);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
