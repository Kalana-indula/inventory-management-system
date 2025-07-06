package org.inventory.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchase_summary")
public class PurchaseSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_purchased")
    private Double totalPurchased;

    @Column(name = "change_percentage")
    private Double changePercentage;

    @Column(name = "date")
    private LocalDate date;
}
