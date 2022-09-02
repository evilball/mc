package com.mc.mc2.route;

import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Mc2Handler {
    
    @Handler
    public McMessage handle(McMessage mcMessage) {
        mcMessage.setMc2Timestamp(LocalDateTime.now());
        return mcMessage;
    }
}
