package org.inventory.app.service;

import org.inventory.app.entity.ExpenseSummary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseSummaryService {

    ExpenseSummary createExpenseSummary(ExpenseSummary expenseSummary);

    List<ExpenseSummary> getAllExpenseSummaries();
}
