package org.inventory.app.service.impl;

import org.inventory.app.entity.SalesSummary;
import org.inventory.app.repository.SalesSummaryRepository;
import org.inventory.app.service.SalesSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesSummaryImpl implements SalesSummaryService {

    private SalesSummaryRepository salesSummaryRepository;

    @Autowired
    public SalesSummaryImpl(SalesSummaryRepository salesSummaryRepository) {
        this.salesSummaryRepository = salesSummaryRepository;
    }

    //create new sales summary
    @Override
    public SalesSummary createSalesSummary(SalesSummary salesSummary) {

        return salesSummaryRepository.save(salesSummary);
    }

    //find all sales
    @Override
    public List<SalesSummary> getAllSalesSummaries() {
        return salesSummaryRepository.findAll();
    }
}
