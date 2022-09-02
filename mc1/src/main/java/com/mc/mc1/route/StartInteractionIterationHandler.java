package com.mc.mc1.route;

import com.mc.mc1.orm.McMessageEntity;
import com.mc.mc1.orm.McMessageRepository;
import com.mc.mc1.rest.message.McMessage;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StartInteractionIterationHandler {

    private final McMessageRepository mcMessages;
    private final ProducerTemplate camel;
    
    public StartInteractionIterationHandler(
            McMessageRepository mcMessages,
            ProducerTemplate camel) {
        this.mcMessages = mcMessages;
        this.camel = camel;
    }

    @Handler
    public void handle(Exchange exchange) {
        McMessageEntity entity = new McMessageEntity();
        entity.setMc1Timestamp(LocalDateTime.now());
        entity.setSessionId(
                Math.toIntExact((Long) exchange.getProperties().get("CamelTimerCounter")));
        mcMessages.save(entity);
        camel.sendBody(Mc2Route.PATH, new McMessage(entity));
    }
}
