package org.inventory.app.repository;

import org.inventory.app.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    //custom method for extract expense details by category
    @Query(value = "SELECT * FROM expenses WHERE category_id = :categoryId", nativeQuery = true)
    List<Expense> findExpensesByCategory(@Param("categoryId") Long categoryId);
}
