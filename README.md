
# Banking Application with Distributed Tracing---Slueth-Zipkin

## Overview

In a modern microservices architecture, distributed tracing helps to monitor and troubleshoot the system by tracking requests across various services. This is crucial for diagnosing issues and understanding performance bottlenecks.

### Significance of Distributed Tracing

- **Performance Monitoring:** Helps in identifying slow services and performance bottlenecks.
- **Troubleshooting:** Makes it easier to trace the path of a request through multiple services, which is valuable for debugging issues.
- **Insight:** Provides insights into how different services interact and the overall system behavior.

## Tools Used

### Zipkin

Zipkin is a distributed tracing system that helps collect and visualize trace data. It provides a web-based UI for viewing trace information, which helps in understanding the flow of requests through different services.

### Spring Cloud Sleuth

Spring Cloud Sleuth provides distributed tracing capabilities for Spring Boot applications. It integrates seamlessly with Zipkin and automatically adds trace and span IDs to logs, making it easier to correlate logs with traces.

## Banking Application Example

In this example, we have a simple banking application that performs operations such as retrieving and updating user accounts. We use Spring Boot with Spring Cloud Sleuth for tracing and Zipkin for visualization.

### Configuration

1. **Add Dependencies**

   Add the following dependencies to your `pom.xml` (for Maven) file:

   ```xml
   <!-- Maven -->
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-sleuth</artifactId>
   </dependency>
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-zipkin</artifactId>
   </dependency>

2. **Run Zipkin Server**

You can run Zipkin locally using Docker:

docker run -d -p 9411:9411 openzipkin/zipkin

3. **View Traces**

Navigate to http://localhost:9411 to view the traces collected by Zipkin.

## Logging
Spring Cloud Sleuth automatically adds trace and span IDs to your logs, which helps in correlating logs with distributed traces. Use a standard logging framework like SLF4J to log messages.

## Logging in a Spring Boot Application
Spring Boot uses SLF4J (Simple Logging Facade for Java) as its logging facade, which can be implemented by various logging frameworks such as Logback, Log4j2, etc. By default, Spring Boot uses Logback for logging.

## Example Controller with Logging

```
package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoggingController {

    private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @GetMapping("/log")
    public String logMessages() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }
}

```
## Sample Log Output

When you make a request to the controller, the log output might look like this:

```

2024-07-22 10:20:15 - com.example.demo.controller.LoggingController - INFO - An INFO Message
2024-07-22 10:20:15 - com.example.demo.controller.LoggingController - WARN - A WARN Message
2024-07-22 10:20:20 - com.example.demo.controller.LoggingController - ERROR - An ERROR Message

```
