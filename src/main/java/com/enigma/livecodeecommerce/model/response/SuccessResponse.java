package com.enigma.livecodeecommerce.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class SuccessResponse<T> extends CommonResponse {
    private T data;
    public SuccessResponse(String message, T data){
        super(message);
        super.setCode("200");
        super.setStatus(HttpStatus.OK.name());
        this.data = data;
    }
}
