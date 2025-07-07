package org.inventory.app.service.impl;

import org.inventory.app.dto.ExpenseDto;
import org.inventory.app.entity.Category;
import org.inventory.app.entity.Expense;
import org.inventory.app.repository.CategoryRepository;
import org.inventory.app.repository.ExpenseRepository;
import org.inventory.app.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;

    private CategoryRepository categoryRepository;

    @Autowired
    public ExpenseServiceImpl(
            ExpenseRepository expenseRepository,
            CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Expense createExpense(ExpenseDto expenseDto) {

        //find existing category
        Category existingCategory=categoryRepository.findById(expenseDto.getCategoryId()).orElse(null);

        if(existingCategory==null){
            return null;
        }

        //create new expense
        Expense expense=new Expense();

        expense.setAmount(expenseDto.getAmount());
        expense.setDate(expenseDto.getDate());
        expense.setCategory(existingCategory);

        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getExpensesByCategory(Long categoryId) {

        //check if the category exists
        boolean isExist=categoryRepository.existsById(categoryId);

        if(!isExist){
            return null;
        }

        return expenseRepository.findExpensesByCategory(categoryId);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
}
