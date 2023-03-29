package com.enigma.livecodeecommerce.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransactionRequest {
    @NotBlank(message = "date is required")
    private String date;
}
