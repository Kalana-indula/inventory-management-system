package org.inventory.app.service;

import org.inventory.app.entity.PurchaseSummary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseSummaryService {

    PurchaseSummary createPurchaseSummary(PurchaseSummary purchaseSummary);

    List<PurchaseSummary> getAllPurchaseSummaries();
}
