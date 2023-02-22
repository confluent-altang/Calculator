package com.altang.calculator.client;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CalculatorExceptionMapper implements ExceptionMapper {
    @Override
    public Response toResponse(final Throwable e) {
        if (e.getClass().equals(IllegalArgumentException.class) || e.getClass().equals(ArithmeticException.class)) {
            return Response.status(422).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}