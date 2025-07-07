package org.inventory.app.controller;

import org.inventory.app.entity.ExpenseSummary;
import org.inventory.app.service.ExpenseSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ExpenseSummaryController {

    private ExpenseSummaryService expenseSummaryService;

    @Autowired
    public ExpenseSummaryController(ExpenseSummaryService expenseSummaryService) {
        this.expenseSummaryService = expenseSummaryService;
    }

    @PostMapping("/expense-summaries")
    public ResponseEntity<?> createExpenseSummary(@RequestBody ExpenseSummary expenseSummary) {
        try{
            ExpenseSummary createdExpenseSummary = expenseSummaryService.createExpenseSummary(expenseSummary);

            return ResponseEntity.status(HttpStatus.OK).body(createdExpenseSummary);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/expense-summaries")
    public ResponseEntity<?> findAllExpenseSummaries() {
        try {
            List<ExpenseSummary> expenseSummaries=expenseSummaryService.getAllExpenseSummaries();

            if(expenseSummaries.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No expense summary found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(expenseSummaries);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
