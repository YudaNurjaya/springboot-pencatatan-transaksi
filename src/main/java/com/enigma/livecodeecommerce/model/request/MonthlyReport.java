package com.enigma.livecodeecommerce.model.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter @Setter
public class MonthlyReport {
    private Integer transaksiId;
    private String startDate;
    private String endDate;
    private String productName;
    private Integer qty;
    private BigDecimal grandTotal;
}
