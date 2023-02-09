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
import com.altang.calculator.responses.AuditResponse;
import io.confluent.rest.annotations.PerformanceMetric;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/audit")
@Produces("application/vnd.audit.v1+json")
public class AuditResource implements AuditResourceInterface {
    private final CalculatorRestConfig config;

    private String log;

    public AuditResource(CalculatorRestConfig config) {
        this.config = config;
        log = "";
    }

    @GET
    @PerformanceMetric("audit")
    public AuditResponse audit() {
        return new AuditResponse(log);
    }

    public void logOperation(final String operation, final String int1, final String int2, final String result) {
        log += String.format("%s,%s,%s,%s\n", operation, int1, int2, result);
    }
}