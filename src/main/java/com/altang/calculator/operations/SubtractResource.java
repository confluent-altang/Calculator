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
import io.confluent.rest.annotations.PerformanceMetric;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/subtract")
@Produces("application/vnd.add.v1+json")
public class SubtractResource extends OperationResource {

  public SubtractResource(final AuditResourceInterface auditResource, final CalculatorRestConfig config) {
    super(auditResource, config);
  }

  @GET
  @PerformanceMetric("subtract-integers")
  public OperationResponse add(@QueryParam("int1") final String int1, @QueryParam("int2") final String int2) {
    return runOperation(int1, int2);
  }

  protected final String calculateResult(final int int1, final int int2) {
    long result = (long)int1 - (long)int2;
    if (result < Integer.MIN_VALUE) {
      return "OVERFLOW";
    }
    return Integer.toString((int)result);
  }

  protected final String getOperationName() {
    return "SUB";
  }
}