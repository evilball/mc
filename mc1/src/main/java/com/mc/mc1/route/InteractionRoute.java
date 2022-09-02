package com.mc.mc1.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InteractionRoute extends RouteBuilder {
    
    public static final String ROUTE_ID = "interaction";
    
    private final int period;
    
    public InteractionRoute(@Value("${mc.period:5}") Integer period) {
        this.period = period;
    }
    
    @Override
    public void configure() throws Exception {
        from("timer:interaction?fixedRate=true&period=" + period())
                .autoStartup(false)
                .routeId(ROUTE_ID)
                .bean("startInteractionIterationHandler");
    }

    private String period() {
        return String.valueOf(period * 1000);
    }
}
