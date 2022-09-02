package com.mc.mc2.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class Mc2Route extends RouteBuilder {
    
    private final ObjectMapper objectMapper;
    
    public Mc2Route(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void configure() throws Exception {
        from("websocket:{{mc.websocket.host}}:{{mc.websocket.port}}/mc2")
                .log(LoggingLevel.DEBUG, "mc","Message received: ${body}")
                .unmarshal(new JacksonDataFormat(objectMapper, McMessage.class))
                .bean("mc2Handler")
                .marshal(new JacksonDataFormat(objectMapper, McMessage.class))
                .log(LoggingLevel.DEBUG, "mc","Sending message: ${body}")
                .to("kafka:mc3?brokers={{mc.kafka.host}}:{{mc.kafka.port}}");
    }
}
