package com.altang.calculator.logic;

import com.altang.calculator.models.OperationModel;

import java.util.ArrayList;
import java.util.List;

public class Auditor implements AuditorInterface {
    private final List<OperationModel> operationList;
    private String log;

    public Auditor() {
        operationList = new ArrayList<OperationModel>();
        log = "";
    }

    public void logOperation(final OperationModel completedOperation) {
        operationList.add(completedOperation);
        log += String.format("%s,%s,%s,%s\n",
                completedOperation.getType(),
                completedOperation.getInt1(),
                completedOperation.getInt2(),
                completedOperation.getResult());
    }

    public String getLog() {
        return log;
    }
}