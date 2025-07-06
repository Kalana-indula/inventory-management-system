package org.inventory.app.service;

import org.inventory.app.dto.PurchaseDto;
import org.inventory.app.entity.Purchase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseService {

    Purchase createPurchase(PurchaseDto purchaseDto);

    List<Purchase> getAllPurchases();
}
