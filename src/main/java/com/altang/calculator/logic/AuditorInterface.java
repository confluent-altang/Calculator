package com.altang.calculator.logic;

import com.altang.calculator.models.OperationModel;

public interface AuditorInterface {

    void logOperation(final OperationModel operation);

    String getLog();
}