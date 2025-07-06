package org.inventory.app.repository;

import org.inventory.app.entity.PurchaseSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseSummaryRepository extends JpaRepository<PurchaseSummary, Long> {
}
