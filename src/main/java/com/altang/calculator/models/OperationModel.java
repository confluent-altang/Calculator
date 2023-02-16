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

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof OperationModel)) {
            return false;
        }

        OperationModel otherOp = (OperationModel) o;
        return type.equals(otherOp.getType()) &&
                int1.equals(otherOp.getInt1()) &&
                int2.equals(otherOp.getInt2()) &&
                result.equals(otherOp.getResult());
    }
}