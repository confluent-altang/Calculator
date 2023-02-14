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
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.glassfish.jersey.servlet.ServletProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.core.Configurable;

import io.confluent.rest.Application;
import io.confluent.rest.RestConfigException;

/**
 * An application represents the configured, running, REST service. You have to provide two things:
 * a configuration (to the constructor or by overriding configure()) and a set of resources to
 * for the REST API (added in setupResources()). After defining these, simply call
 * Application.createServer() to get a Jetty server, then call start() to start processing requests
 *
 * <p>This application uses a simple configuration that allows you to override the message that is
 * echoed back in the response, and the driver program optionally loads this setting from a command
 * line argument.
 */
public class CalculatorApplication extends Application<CalculatorRestConfig> {
    private static final Logger log = LoggerFactory.getLogger(CalculatorApplication.class);

    public CalculatorApplication(CalculatorRestConfig config) {
        super(config);
    }

    @Override
    public void setupResources(Configurable<?> config, CalculatorRestConfig appConfig) {
        AuditorInterface auditor = new Auditor();
        CalculatorBrainInterface brain = new CalculatorBrain(auditor);
        config.register(new AuditResource(appConfig, auditor));
        config.register(new AddResource(appConfig, brain));
        config.register(new SubtractResource(appConfig, brain));
        config.register(new MultiplyResource(appConfig, brain));
        config.register(new DivideResource(appConfig, brain));
        config.property(ServletProperties.FILTER_STATIC_CONTENT_REGEX, "/(static/.*|.*\\.html|)");
    }

    @Override
    public Map<String, String> getMetricsTags() {
        Map<String, String> tags = new LinkedHashMap<>();
        tags.put("instance-id", "1");
        return tags;
    }

    @Override
    protected ResourceCollection getStaticResources() {
        return new ResourceCollection(Resource.newClassPathResource("static"));
    }

    public static void main(String[] args) {
        try {
            TreeMap<String, String> settings = new TreeMap<>();
            CalculatorRestConfig config = new CalculatorRestConfig(settings);
            CalculatorApplication app = new CalculatorApplication(config);
            app.start();
            log.info("Server started, listening for requests...");
            app.join();
        } catch (RestConfigException e) {
            log.error("Server configuration failed: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            log.error("Server died unexpectedly: " + e);
        }
    }
}