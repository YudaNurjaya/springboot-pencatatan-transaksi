package com.enigma.livecodeecommerce.model.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class DailyReport {
    private Integer transaksiId;
    private String date;
    private String productName;
    private Integer qty;
    private BigDecimal grandTotal;

    public DailyReport() {
        this.grandTotal = BigDecimal.ZERO;
    }
}
