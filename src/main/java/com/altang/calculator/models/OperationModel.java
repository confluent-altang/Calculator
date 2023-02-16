package com.altang.calculator.models;

public class OperationModel {
    private final OperationType type;
    private final String int1;
    private final String int2;
    private OperationResult result;

    public OperationModel(final OperationType type, final String int1, final String int2) {
        this.type = type;
        this.int1 = int1;
        this.int2 = int2;
    }

    public OperationType getType() {
        return type;
    }

    public String getInt1() {
        return int1;
    }

    public String getInt2() {
        return int2;
    }

    public OperationResult getResult() {
        return result;
    }

    public void setResult(final OperationResult result) {
        this.result = result;
    }
}