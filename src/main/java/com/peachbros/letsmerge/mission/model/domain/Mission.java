package com.peachbros.letsmerge.mission.model.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDateTime startDateTime;
    @Column(nullable = false)
    private LocalDateTime dueDateTime;

    public Mission() {
    }

    public Mission(String name, LocalDateTime dueDateTime) {
        this(name, LocalDateTime.now(), dueDateTime);
    }

    public Mission(String name, LocalDateTime startDateTime, LocalDateTime dueDateTime) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.dueDateTime = dueDateTime;
    }

    public boolean isActive(LocalDateTime now) {
        return startDateTime.isBefore(now) && dueDateTime.isAfter(now);
    }

    public boolean isNotActive(LocalDateTime now) {
        return !isActive(now);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }
}