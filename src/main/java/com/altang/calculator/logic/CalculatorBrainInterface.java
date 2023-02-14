package com.altang.calculator.logic;

import com.altang.calculator.models.OperationModel;
import com.altang.calculator.models.OperationResult;

public interface CalculatorBrainInterface {
    OperationResult compute(final OperationModel operationModel);
}