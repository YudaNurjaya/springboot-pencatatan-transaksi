package com.enigma.livecodeecommerce.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class ProductRequest {
    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "stock is required")
    private Integer stock;
    @NotNull(message = "price is required")
    private Double price;
    @NotNull(message = "categoryId is required")
    private Integer categoryId;
}
