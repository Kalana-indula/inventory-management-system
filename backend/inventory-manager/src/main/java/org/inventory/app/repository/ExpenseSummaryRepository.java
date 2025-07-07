package org.inventory.app.repository;

import org.inventory.app.entity.ExpenseSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseSummaryRepository extends JpaRepository<ExpenseSummary, Long> {
}
