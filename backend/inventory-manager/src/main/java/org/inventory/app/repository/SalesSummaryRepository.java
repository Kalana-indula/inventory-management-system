package org.inventory.app.repository;

import org.inventory.app.entity.SalesSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesSummaryRepository extends JpaRepository<SalesSummary, Long> {
}
