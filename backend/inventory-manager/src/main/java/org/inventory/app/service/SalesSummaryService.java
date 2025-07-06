package org.inventory.app.service;

import org.inventory.app.entity.SalesSummary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalesSummaryService {
    SalesSummary createSalesSummary(SalesSummary salesSummary);

    List<SalesSummary> getAllSalesSummaries();
}
