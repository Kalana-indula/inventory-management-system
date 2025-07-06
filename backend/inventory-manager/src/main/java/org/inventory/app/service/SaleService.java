package org.inventory.app.service;

import org.inventory.app.dto.SaleDto;
import org.inventory.app.entity.Sale;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SaleService {
    Sale createSale(SaleDto saleDto);

    List<Sale> getAllSales();
}
