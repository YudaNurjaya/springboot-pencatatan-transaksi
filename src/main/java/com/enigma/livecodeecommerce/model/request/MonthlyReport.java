package com.enigma.livecodeecommerce.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter
public class MonthlyReport {
    private Integer transaksiId;
    @JsonIgnore
    private String startDate;
    @JsonIgnore
    private String endDate;
    private Date date;
    private String productName;
    private Integer qty;
    private BigDecimal grandTotal;
}
