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

package com.altang.calculator.client;

import com.altang.calculator.CalculatorRestConfig;
import com.altang.calculator.logic.CalculatorBrainInterface;
import com.altang.calculator.models.OperationModel;
import com.altang.calculator.models.OperationType;
import io.confluent.rest.annotations.PerformanceMetric;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/subtract")
@Produces("application/vnd.subtract.v1+json")
public class SubtractResource {
    private final CalculatorRestConfig config;
    private final CalculatorBrainInterface brain;

    public SubtractResource(final CalculatorRestConfig config, final CalculatorBrainInterface brain) {
        this.config = config;
        this.brain = brain;
    }

    @GET
    @PerformanceMetric("add-integers")
    public String subtract(@QueryParam("int1") final String int1, @QueryParam("int2") final String int2) {
        OperationModel operation = new OperationModel(OperationType.SUBTRACT, int1, int2);
        return brain.compute(operation).toString();
    }
}