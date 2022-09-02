package com.mc.mc1.rest;

import com.mc.mc1.orm.McMessageRepository;
import com.mc.mc1.rest.message.McMessage;
import com.mc.mc1.route.InteractionRoute;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class Interaction {
    
    private static final Logger LOG = LoggerFactory.getLogger(Interaction.class);
    
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicLong startTime = new AtomicLong(); 
    private final AtomicLong counter = new AtomicLong();
    private final ProducerTemplate camel;
    private final McMessageRepository mcMessages;
    
    public Interaction(ProducerTemplate camel,
                       McMessageRepository mcMessages
                       ) {
        this.camel = camel;
        this.mcMessages = mcMessages;
    }
    
    public void start() {
        synchronized (running) {
            if (!running.get()) {
                LOG.info("Starting interaction...");
                camel.sendBody(
                        "controlbus:route?routeId=" + InteractionRoute.ROUTE_ID + "&action=start",
                        null);
                running.set(true);
                startTime.set(System.currentTimeMillis());
                counter.set(0);
            } else {
                LOG.warn("Interaction already running!");
            }
        }
    }
    
    public void stop() {
        synchronized (running) {
            if (running.get()) {
                LOG.info("Finishing interaction...");
                LOG.info("Ineraction time: {} seconds", (System.currentTimeMillis() - startTime.get()) / 1000);
                LOG.info("Iterations: {}", counter.get());
                camel.requestBody("controlbus:route?routeId=" + InteractionRoute.ROUTE_ID + "&action=stop", (Object) null);
                running.set(false);
            } else {
                LOG.warn("Interaction is not running!");
            }
        }
    }

    @Transactional
    public void finishMessage(McMessage mcMessage) {
        mcMessages.findById(mcMessage.getId())
                .map(message -> {
                    message.setMc2Timestamp(mcMessage.getMc2Timestamp());
                    message.setMc3Timestamp(mcMessage.getMc3Timestamp());
                    message.setEndTimestamp(LocalDateTime.now());
                    mcMessages.save(message);
                    return counter.incrementAndGet();
                }
        ).orElseThrow();
    }
}

