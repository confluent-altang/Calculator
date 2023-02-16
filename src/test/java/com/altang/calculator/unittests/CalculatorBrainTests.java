package com.altang.calculator.unittests;

import com.altang.calculator.logic.Auditor;
import com.altang.calculator.logic.CalculatorBrain;
import com.altang.calculator.logic.CalculatorErrorStrings;
import com.altang.calculator.models.OperationModel;
import com.altang.calculator.models.OperationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorBrainTests {
    @Test
    public void testAdd_withParams_returnsSum() {
        Auditor auditor = new Auditor();
        CalculatorBrain brain = new CalculatorBrain(auditor);
        OperationModel model = new OperationModel(OperationType.ADD, "2", "3");

        brain.compute(model);

        assertEquals("5", model.getResult().toString());
    }

    @Test
    public void testAdd_withNonIntParams_returnsError() {
        Auditor auditor = new Auditor();
        CalculatorBrain brain = new CalculatorBrain(auditor);
        OperationModel model = new OperationModel(OperationType.ADD, "HELLO", "WORLD");

        brain.compute(model);

        assertEquals(CalculatorErrorStrings.ARGUMENT, model.getResult().toString());
    }

    @Test
    public void testAdd_withOverflowingParams_returnsError() {
        Auditor auditor = new Auditor();
        CalculatorBrain brain = new CalculatorBrain(auditor);
        OperationModel model = new OperationModel(OperationType.ADD, Integer.toString(Integer.MAX_VALUE), Integer.toString(Integer.MAX_VALUE));

        brain.compute(model);

        assertEquals(CalculatorErrorStrings.OVERFLOW, model.getResult().toString());
    }

    @Test
    public void testSubtract_withParams_returnsDifference() {
        Auditor auditor = new Auditor();
        CalculatorBrain brain = new CalculatorBrain(auditor);
        OperationModel model = new OperationModel(OperationType.SUBTRACT, "4", "1");

        brain.compute(model);

        assertEquals("3", model.getResult().toString());
    }

    @Test
    public void testSubtract_withNonIntParams_returnsError() {
        Auditor auditor = new Auditor();
        CalculatorBrain brain = new CalculatorBrain(auditor);
        OperationModel model = new OperationModel(OperationType.SUBTRACT, "HELLO", "WORLD");

        brain.compute(model);

        assertEquals(CalculatorErrorStrings.ARGUMENT, model.getResult().toString());
    }

    @Test
    public void testSubtract_withOverflowingParams_returnsError() {
        Auditor auditor = new Auditor();
        CalculatorBrain brain = new CalculatorBrain(auditor);
        OperationModel model = new OperationModel(OperationType.SUBTRACT, Integer.toString(Integer.MIN_VALUE), Integer.toString(Integer.MAX_VALUE));

        brain.compute(model);

        assertEquals(CalculatorErrorStrings.OVERFLOW, model.getResult().toString());
    }

    @Test
    public void testMultiply_withParams_returnsProduct() {
        Auditor auditor = new Auditor();
        CalculatorBrain brain = new CalculatorBrain(auditor);
        OperationModel model = new OperationModel(OperationType.MULTIPLY, "2", "3");

        brain.compute(model);

        assertEquals("6", model.getResult().toString());
    }

    @Test
    public void testMultiply_withNonIntParams_returnsError() {
        Auditor auditor = new Auditor();
        CalculatorBrain brain = new CalculatorBrain(auditor);
        OperationModel model = new OperationModel(OperationType.ADD, "HELLO", "WORLD");

        brain.compute(model);

        assertEquals(CalculatorErrorStrings.ARGUMENT, model.getResult().toString());
    }

    @Test
    public void testMultiply_withOverflowingParams_returnsError() {
        Auditor auditor = new Auditor();
        CalculatorBrain brain = new CalculatorBrain(auditor);
        OperationModel model = new OperationModel(OperationType.MULTIPLY, Integer.toString(Integer.MAX_VALUE), Integer.toString(Integer.MAX_VALUE));

        brain.compute(model);

        assertEquals(CalculatorErrorStrings.OVERFLOW, model.getResult().toString());
    }

    @Test
    public void testDivide_withParams_returnsIntegerQuotient() {
        Auditor auditor = new Auditor();
        CalculatorBrain brain = new CalculatorBrain(auditor);
        OperationModel model = new OperationModel(OperationType.DIVIDE, "7", "3");

        brain.compute(model);

        assertEquals("2", model.getResult().toString());
    }

    @Test
    public void testDivide_withNonIntParams_returnsError() {
        Auditor auditor = new Auditor();
        CalculatorBrain brain = new CalculatorBrain(auditor);
        OperationModel model = new OperationModel(OperationType.DIVIDE, "HELLO", "WORLD");

        brain.compute(model);

        assertEquals(CalculatorErrorStrings.ARGUMENT, model.getResult().toString());
    }

    @Test
    public void testDivide_withZeroDivisor_returnsDivideByZeroError() {
        Auditor auditor = new Auditor();
        CalculatorBrain brain = new CalculatorBrain(auditor);
        OperationModel model = new OperationModel(OperationType.DIVIDE, "5", "0");

        brain.compute(model);

        assertEquals(CalculatorErrorStrings.DIVIDE_BY_O, model.getResult().toString());
    }
}