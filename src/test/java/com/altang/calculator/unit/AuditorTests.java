package com.altang.calculator.unit;

import com.altang.calculator.logic.Auditor;
import com.altang.calculator.logic.CalculatorErrorStrings;
import com.altang.calculator.models.OperationModel;
import com.altang.calculator.models.OperationResult;
import com.altang.calculator.models.OperationType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuditorTests {
    @Test
    public void testAuditor_savesAllCalculatorModels_whenGivenCalculatorModels() {
        Auditor auditor = new Auditor();
        List<OperationModel> exampleOperations = getExampleOperations();

        for (OperationModel operation : exampleOperations) {
            auditor.logOperation(operation);
        }

        assertEquals(getExampleOperationsString(), auditor.getLog());
    }

    private List<OperationModel> getExampleOperations() {
        List<OperationModel> operations = new ArrayList<>();

        OperationModel operation1 = new OperationModel(OperationType.ADD, "1", "2");
        operation1.setResult(new OperationResult(3));
        operations.add(operation1);

        OperationModel operation2 = new OperationModel(OperationType.MULTIPLY, "2", "3");
        operation2.setResult(new OperationResult(6));
        operations.add(operation2);

        OperationModel operation3 = new OperationModel(OperationType.DIVIDE, "5", "0");
        operation3.setResult(new OperationResult(new ArithmeticException(CalculatorErrorStrings.DIVIDE_BY_O)));
        operations.add(operation3);

        return operations;
    }

    private String getExampleOperationsString() {
        return OperationType.ADD + ",1,2,3\n" +
                OperationType.MULTIPLY + ",2,3,6\n" +
                OperationType.DIVIDE + ",5,0," + CalculatorErrorStrings.DIVIDE_BY_O + "\n";
    }
}