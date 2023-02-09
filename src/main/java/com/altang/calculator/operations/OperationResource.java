/*
 * Copyright 2014 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.altang.calculator.operations;

import com.altang.calculator.CalculatorRestConfig;
import com.altang.calculator.responses.OperationResponse;

import javax.ws.rs.QueryParam;

public abstract class OperationResource {
    protected final AuditResourceInterface auditResource;
    protected final CalculatorRestConfig config;

    public OperationResource(final AuditResourceInterface auditResource, final CalculatorRestConfig config) {
        this.auditResource = auditResource;
        this.config = config;
    }

    public final OperationResponse runOperation(@QueryParam("int1") final int int1, @QueryParam("int2") final int int2) {
        String result = calculateResult(int1, int2);
        auditResource.logOperation(getOperationName(), int1, int2, result);
        return new OperationResponse(result);
    }

    protected abstract String calculateResult(final int int1, final int int2);

    protected abstract String getOperationName();
}