package org.inventory.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SaleDto {
    private Long productId;
    private Integer quantity;
}
