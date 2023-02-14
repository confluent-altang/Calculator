package com.altang.calculator.logic;

import com.altang.calculator.models.OperationModel;
import com.altang.calculator.models.OperationResult;

import java.util.ArrayList;
import java.util.List;

public class CalculatorBrain implements CalculatorBrainInterface {
    private final AuditorInterface auditor;

    public CalculatorBrain(final AuditorInterface auditor) {
        this.auditor = auditor;
    }

    public OperationResult compute(final OperationModel operationModel) {
        List<Integer> arguments;
        int resultValue = 0;
        OperationResult result = null;
        try {
            arguments = deserializeArguments(operationModel);
            switch (operationModel.getType()) {
                case ADD:
                    resultValue = computeAdd(arguments);
                    break;
                case SUBTRACT:
                    resultValue = computeSubtract(arguments);
                    break;
                case MULTIPLY:
                    resultValue = computeMultiply(arguments);
                    break;
                case DIVIDE:
                    resultValue = computeDivide(arguments);
                    break;
            }
            result = new OperationResult(resultValue);
        } catch (NumberFormatException e) {
            result = new OperationResult(new IllegalArgumentException("ARGUMENT"));
        } catch (ArithmeticException e) {
            result = new OperationResult(e);
        }
        auditor.logOperation(new OperationModel(operationModel, result));
        return result;
    }

    private List<Integer> deserializeArguments(final OperationModel operationModel) throws NumberFormatException {
        List<Integer> arguments = new ArrayList<>();
        arguments.add(Integer.parseInt(operationModel.getInt1()));
        arguments.add(Integer.parseInt(operationModel.getInt2()));
        return arguments;
    }

    private int computeAdd(final List<Integer> arguments) throws ArithmeticException {
        long result = 0;
        for (int argument : arguments) {
            result += argument;
        }
        checkIntegerBounds(result);
        return (int) result;
    }

    private int computeSubtract(final List<Integer> arguments) throws ArithmeticException {
        long result = arguments.get(0);
        for (int i = 1; i < arguments.size(); i++) {
            int argument = arguments.get(i);
            result -= argument;
        }
        checkIntegerBounds(result);
        return (int) result;
    }

    private int computeMultiply(final List<Integer> arguments) throws ArithmeticException {
        long result = 1;
        for (int argument : arguments) {
            result *= argument;
        }
        checkIntegerBounds(result);
        return (int) result;
    }

    private int computeDivide(final List<Integer> arguments) throws ArithmeticException {
        long result = arguments.get(0);
        for (int i = 1; i < arguments.size(); i++) {
            int argument = arguments.get(i);
            if (argument == 0) {
                throw new ArithmeticException("DIV BY 0");
            }
            result /= argument;
        }
        checkIntegerBounds(result);
        return (int) result;
    }

    private void checkIntegerBounds(final long value) throws ArithmeticException {
        if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
            throw new ArithmeticException("OVERFLOW");
        }
    }
}