package com.altang.calculator.unittests;

import com.altang.calculator.logic.Auditor;
import com.altang.calculator.logic.CalculatorBrain;
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

        assertEquals("ARGUMENT", model.getResult().toString());
    }

    @Test
    public void testAdd_withOverflowingParams_returnsError() {
        Auditor auditor = new Auditor();
        CalculatorBrain brain = new CalculatorBrain(auditor);
        OperationModel model = new OperationModel(OperationType.ADD, "999999999999", "999999999999");

        brain.compute(model);

        assertEquals("OVERFLOW", model.getResult().toString());
    }
}