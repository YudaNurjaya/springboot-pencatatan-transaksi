package com.enigma.livecodeecommerce.model.request;

import com.enigma.livecodeecommerce.model.Transaction;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransactionDetailRequest {
    @NotNull(message = "quantity is required")
    private Integer qty;
    @NotNull(message = "productId is required")
    private Integer productId;
    @NotNull(message = "transactionId is required")
    private Transaction transactionId;
}
