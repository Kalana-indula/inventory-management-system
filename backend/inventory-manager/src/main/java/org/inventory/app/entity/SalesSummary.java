package org.inventory.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales_summary")
@CrossOrigin(origins = "*")
public class SalesSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_value")
    private Double totalValue;

    @Column(name = "change_percentage")
    private Double changePercentage;

    @Column(name = "date")
    private LocalDate date;
}
