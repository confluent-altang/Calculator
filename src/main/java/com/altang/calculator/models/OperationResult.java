package com.altang.calculator.models;

public class OperationResult {
    private int result;
    private Exception exception;

    public OperationResult(final int result) {
        this.result = result;
    }

    public OperationResult(final Exception exception) {
        this.exception = exception;
    }

    public int getResult() {
        return result;
    }

    public Exception getException() {
        return exception;
    }

    @Override
    public String toString() {
        if (exception == null) {
            return Integer.toString(result);
        } else {
            return exception.getMessage();
        }
    }
}