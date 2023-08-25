package com.project.OrderService.Errors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.OrderService.Models.ErrorBody;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ErrorBody errorBody = objectMapper.readValue(response.body().asInputStream(), ErrorBody.class);
            return new CustomError(errorBody.getMessage(), errorBody.getResolution());
        } catch (IOException e) {
            throw new CustomError("SOMETHING WEIRD","TRY OTHERS");
        }
    }
}
