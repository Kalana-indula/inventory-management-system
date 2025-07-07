package org.inventory.app.service.impl;

import org.inventory.app.entity.ExpenseSummary;
import org.inventory.app.repository.ExpenseSummaryRepository;
import org.inventory.app.service.ExpenseSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseSummaryServiceImpl implements ExpenseSummaryService {

    private ExpenseSummaryRepository expenseSummaryRepository;

    @Autowired
    public ExpenseSummaryServiceImpl(ExpenseSummaryRepository expenseSummaryRepository) {
        this.expenseSummaryRepository = expenseSummaryRepository;
    }

    @Override
    public ExpenseSummary createExpenseSummary(ExpenseSummary expenseSummary) {
        return expenseSummaryRepository.save(expenseSummary);
    }

    @Override
    public List<ExpenseSummary> getAllExpenseSummaries() {
        return expenseSummaryRepository.findAll();
    }
}
