package org.inventory.app.service;

import org.inventory.app.dto.ExpenseDto;
import org.inventory.app.entity.Expense;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {

    Expense createExpense(ExpenseDto expenseDto);

    List<Expense> getExpensesByCategory(Long categoryId);

    List<Expense> getAllExpenses();
}
