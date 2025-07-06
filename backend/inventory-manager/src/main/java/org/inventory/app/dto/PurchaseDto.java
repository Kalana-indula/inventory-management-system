package org.inventory.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PurchaseDto {
    private Long productId;
    private Integer quantity;
}
