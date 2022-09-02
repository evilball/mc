package com.mc.mc3.route;

import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Mc3Handler {

    @Handler
    public McMessage handle(McMessage mcMessage) {
        mcMessage.setMc3Timestamp(LocalDateTime.now());
        return mcMessage;
    }
}
