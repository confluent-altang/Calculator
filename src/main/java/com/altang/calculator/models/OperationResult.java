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

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof OperationResult)) {
            return false;
        }

        OperationResult otherResult = (OperationResult) o;
        if (result == otherResult.getResult()) {
            Exception otherException = otherResult.getException();
            if (exception == null && otherException == null) {
                return true;
            } else if (exception != null && otherException != null) {
                return exception.getClass().equals(otherException.getClass()) && exception.getMessage().equals(otherException.getMessage());
            }
        }

        return false;
    }
}