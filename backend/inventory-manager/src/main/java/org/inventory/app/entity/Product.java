package org.inventory.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "product")
    private List<Sale> sales;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "product")
    private List<Purchase> purchases;
}
