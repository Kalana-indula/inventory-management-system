package org.inventory.app.service.impl;

import org.inventory.app.entity.PurchaseSummary;
import org.inventory.app.repository.PurchaseSummaryRepository;
import org.inventory.app.service.PurchaseSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseSummaryServiceImpl implements PurchaseSummaryService {

    private PurchaseSummaryRepository purchaseSummaryRepository;

    @Autowired
    public PurchaseSummaryServiceImpl(PurchaseSummaryRepository purchaseSummaryRepository) {
        this.purchaseSummaryRepository = purchaseSummaryRepository;
    }

    @Override
    public PurchaseSummary createPurchaseSummary(PurchaseSummary purchaseSummary) {
        return purchaseSummaryRepository.save(purchaseSummary);
    }

    @Override
    public List<PurchaseSummary> getAllPurchaseSummaries() {
        return purchaseSummaryRepository.findAll();
    }
}
