package com.mc.mc1.rest;

import com.mc.mc1.rest.message.McMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mc1Controller {
    private final Interaction interaction;
    
    public Mc1Controller(Interaction interaction) {
        this.interaction = interaction;
    }
    
    @GetMapping(path = "start")
    public ResponseEntity<?> start() {
        interaction.start();
        return ResponseEntity.ok().build();
    }
    
    @GetMapping(path = "stop")
    public ResponseEntity<?> stop() {
        interaction.stop();
        return ResponseEntity.ok().build();
    }
    
    @PostMapping(path = "end_message")
    public ResponseEntity<?> endMessage(@RequestBody McMessage mcMessage) {
        interaction.finishMessage(mcMessage);
        return ResponseEntity.ok().build();
    }
    
    
}
