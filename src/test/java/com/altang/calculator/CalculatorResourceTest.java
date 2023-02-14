/**
 * Copyright 2014 Confluent Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.altang.calculator;

import com.altang.calculator.client.AddResource;
import com.altang.calculator.client.AuditResource;
import com.altang.calculator.client.DivideResource;
import com.altang.calculator.client.MultiplyResource;
import com.altang.calculator.client.SubtractResource;
import com.altang.calculator.logic.Auditor;
import com.altang.calculator.logic.AuditorInterface;
import com.altang.calculator.logic.CalculatorBrain;
import com.altang.calculator.logic.CalculatorBrainInterface;
import com.altang.calculator.models.OperationModel;
import com.altang.calculator.models.OperationResult;
import com.altang.calculator.responses.OperationResponse;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import io.confluent.rest.RestConfigException;
import io.confluent.rest.EmbeddedServerTestHarness;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This tests a single resource by setting up a Jersey-based test server embedded in the application.
 * This is intended to unit test a single resource class, not the entire collection of resources.
 * EmbeddedServerTestHarness does most of the heavy lifting so you only need to issue requests and
 * check responses.
 */
public class CalculatorResourceTest extends EmbeddedServerTestHarness<CalculatorRestConfig, CalculatorApplication> {
    private final static String addMediaType = "application/vnd.add.v1+json";
    private final static String subtractMediaType = "application/vnd.subtract.v1+json";
    private final static String multiplyMediaType = "application/vnd.multiply.v1+json";
    private final static String divideMediaType = "application/vnd.divide.v1+json";

    public CalculatorResourceTest() throws RestConfigException {
        AuditorInterface auditor = new Auditor();
        CalculatorBrainInterface brain = new CalculatorBrain(auditor);
        addResource(new AddResource(config, brain));
        addResource(new SubtractResource(config, brain));
        addResource(new MultiplyResource(config, brain));
        addResource(new DivideResource(config, brain));
    }

    @Test
    public void testAdd() {
        Response response = request("/add", addMediaType).get();
        // The response should indicate success and have the expected content type
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(addMediaType, response.getMediaType().toString());

        // We should also be able to parse it as the expected output format
        final OperationResponse message = response.readEntity(OperationResponse.class);
        // And it should contain the expected message
        assertEquals("ARGUMENT", message.getMessage());
    }
}