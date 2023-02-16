package com.altang.calculator.unit;

import com.altang.calculator.logic.CalculatorErrorStrings;
import com.altang.calculator.models.OperationModel;
import com.altang.calculator.models.OperationResult;
import com.altang.calculator.models.OperationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EqualityTests {
    @Test
    public void testOperationResultEquality_isTrue_whenSameObject() {
        OperationResult result = new OperationResult(5);

        assertEquals(result, result);
    }

    @Test
    public void testOperationResultEquality_isTrue_whenDifferentObjectsSameFields() {
        OperationResult result1 = new OperationResult(new IllegalArgumentException(CalculatorErrorStrings.ARGUMENT));
        OperationResult result2 = new OperationResult(new IllegalArgumentException(CalculatorErrorStrings.ARGUMENT));

        assertEquals(result1, result2);
    }

    @Test
    public void testOperationResultEquality_isFalse_whenDifferentObjectsDifferentFields() {
        OperationResult result1 = new OperationResult(new IllegalArgumentException(CalculatorErrorStrings.ARGUMENT));
        OperationResult result2 = new OperationResult(new ArithmeticException(CalculatorErrorStrings.ARGUMENT));

        assertNotEquals(result1, result2);
    }

    @Test
    public void testOperationModelEquality_isTrue_whenSameObject() {
        OperationResult result = new OperationResult(5);
        OperationModel operation = new OperationModel(OperationType.ADD, "2", "3");
        operation.setResult(result);

        assertEquals(operation, operation);
    }

    @Test
    public void testOperationModelEquality_isTrue_whenDifferentObjectsSameFields() {
        OperationResult result1 = new OperationResult(new IllegalArgumentException(CalculatorErrorStrings.ARGUMENT));
        OperationModel operation1 = new OperationModel(OperationType.ADD, "2", "3");
        operation1.setResult(result1);
        OperationResult result2 = new OperationResult(new IllegalArgumentException(CalculatorErrorStrings.ARGUMENT));
        OperationModel operation2 = new OperationModel(OperationType.ADD, "2", "3");
        operation2.setResult(result2);

        assertEquals(operation1, operation2);
    }

    @Test
    public void testOperationModelEquality_isFalse_whenDifferentObjectsDifferentFields() {
        OperationResult result1 = new OperationResult(5);
        OperationModel operation1 = new OperationModel(OperationType.ADD, "2", "3");
        operation1.setResult(result1);
        OperationResult result2 = new OperationResult(5);
        OperationModel operation2 = new OperationModel(OperationType.ADD, "3", "2");
        operation2.setResult(result2);

        assertNotEquals(operation1, operation2);
    }
}