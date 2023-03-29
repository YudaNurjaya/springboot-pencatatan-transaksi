package com.enigma.livecodeecommerce.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryRequest {
    @NotBlank(message = "name is required")
    private String name;
}
