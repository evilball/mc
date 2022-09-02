package com.mc.mc1.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mc.mc1.rest.message.McMessage;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class Mc2Route extends RouteBuilder {
    
    public static final String ROUTE_ID = "mc2";
    public static final String PATH = "seda:" + ROUTE_ID;
    
    private final ObjectMapper objectMapper;
    
    public Mc2Route(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    @Override
    public void configure() throws Exception {
        from(PATH)
                .routeId(ROUTE_ID)
                .marshal(new JacksonDataFormat(objectMapper, McMessage.class))
                .log(LoggingLevel.DEBUG, "mc", "Sending message: ${body}")
                .to("ahc-ws:{{mc.websocket.host}}:{{mc.websocket.port}}/mc2?sendToAll=true");
    }
    
}
