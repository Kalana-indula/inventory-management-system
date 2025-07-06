package org.inventory.app.controller;

import org.inventory.app.entity.SalesSummary;
import org.inventory.app.service.SalesSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SalesSummaryController {

    private SalesSummaryService salesSummaryService;

    @Autowired
    public SalesSummaryController(SalesSummaryService salesSummaryService) {
        this.salesSummaryService = salesSummaryService;
    }

    @PostMapping("/sales-summaries")
    public ResponseEntity<?> createSalesSummary(@RequestBody SalesSummary salesSummary) {
        try{
            SalesSummary createdSalesSummary = salesSummaryService.createSalesSummary(salesSummary);

            return ResponseEntity.status(HttpStatus.OK).body(createdSalesSummary);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/sales_summaries")
    public ResponseEntity<?> getAllSalesSummaries() {
        try{
            List<SalesSummary> salesSummaries = salesSummaryService.getAllSalesSummaries();

            if(salesSummaries.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No sales summaries found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(salesSummaries);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
