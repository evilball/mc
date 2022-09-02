package com.mc.mc1.rest.message;

import com.mc.mc1.orm.McMessageEntity;

import java.time.LocalDateTime;

public class McMessage {
    private Long id;
    private Integer sessionId;
    private LocalDateTime mc1Timestamp;
    private LocalDateTime mc2Timestamp;
    private LocalDateTime mc3Timestamp;
    
    public McMessage() {}
    
    public McMessage(McMessageEntity mcMessage) {
        id = mcMessage.getId();
        sessionId = mcMessage.getSessionId();
        mc1Timestamp = mcMessage.getMc1Timestamp();
        mc2Timestamp = mcMessage.getMc2Timestamp();
        mc3Timestamp = mcMessage.getMc3Timestamp();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getMc1Timestamp() {
        return mc1Timestamp;
    }

    public void setMc1Timestamp(LocalDateTime mc1Timestamp) {
        this.mc1Timestamp = mc1Timestamp;
    }

    public LocalDateTime getMc2Timestamp() {
        return mc2Timestamp;
    }

    public void setMc2Timestamp(LocalDateTime mc2Timestamp) {
        this.mc2Timestamp = mc2Timestamp;
    }

    public LocalDateTime getMc3Timestamp() {
        return mc3Timestamp;
    }

    public void setMc3Timestamp(LocalDateTime mc3Timestamp) {
        this.mc3Timestamp = mc3Timestamp;
    }
}
