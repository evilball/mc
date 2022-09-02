package com.mc.mc3.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class Mc3Route extends RouteBuilder {
    
    private final ObjectMapper objectMapper;
    
    public Mc3Route(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    @Override
    public void configure() throws Exception {
         from("kafka:mc3?brokers={{mc.kafka.host}}:{{mc.kafka.port}}")
                 .log(LoggingLevel.DEBUG, "mc","Message received: ${body}")
                 .unmarshal(new JacksonDataFormat(objectMapper, McMessage.class))
                 .bean("mc3Handler")
                 .marshal(new JacksonDataFormat(objectMapper, McMessage.class))
                 .log(LoggingLevel.DEBUG, "mc", "Sending message: ${body}")
                 .to("http:{{mc.mc1.host}}:{{mc.mc1.port}}/end_message");
    }
}
