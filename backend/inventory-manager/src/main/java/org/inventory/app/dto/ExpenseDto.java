package org.inventory.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ExpenseDto {
    private Integer amount;
    private LocalDateTime date;
    private Long categoryId;
}
