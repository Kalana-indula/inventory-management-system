package org.inventory.app.controller;

import org.inventory.app.dto.ExpenseDto;
import org.inventory.app.entity.Expense;
import org.inventory.app.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ExpenseController {

    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/expenses")
    public ResponseEntity<?> createExpense(@RequestBody ExpenseDto expenseDto) {
        try{
            Expense createdExpense=expenseService.createExpense(expenseDto);

            return ResponseEntity.status(HttpStatus.OK).body(createdExpense);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/categories/{categoryId}/expenses")
    public ResponseEntity<?> findExpensesByCategory(@PathVariable Long categoryId){
        try{
            List<Expense> expenses=expenseService.getExpensesByCategory(categoryId);

            if(expenses==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No categories found");
            }

            if(expenses.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Expenses found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(expenses);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/expenses")
    public ResponseEntity<?> finAllExpenses() {
        try{
            List<Expense> expenses=expenseService.getAllExpenses();

            if(expenses.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No expenses found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(expenses);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
