package com.mc.mc1.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "mc_message")
public class McMessageEntity {

    @Id
    @SequenceGenerator(
            name = "McMessageIdGenerator",
            sequenceName = "mc_message_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "McMessageIdGenerator")
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @Column(name = "session_id")
    private Integer sessionId;

    @Column(name = "mc1_timestamp")
    private LocalDateTime mc1Timestamp;

    @Column(name = "mc2_timestamp")
    private LocalDateTime mc2Timestamp;

    @Column(name = "mc3_timestamp")
    private LocalDateTime mc3Timestamp;

    @Column(name = "end_timestamp")
    private LocalDateTime endTimestamp;

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

    public LocalDateTime getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(LocalDateTime endTimestamp) {
        this.endTimestamp = endTimestamp;
    }
}
