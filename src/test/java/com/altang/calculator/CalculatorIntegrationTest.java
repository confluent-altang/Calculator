package com.altang.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorIntegrationTest {
    @Test
    public void testAdd() {
        RetrofitClient client = new RetrofitClient();
        CallbackResultStore store = client.add("1", "2");
        /*while (!store.isRequestCompleted()) {
        }*/
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
        assertEquals("3", store.getMessage());
    }
}