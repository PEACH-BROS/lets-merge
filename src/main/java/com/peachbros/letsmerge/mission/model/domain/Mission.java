package com.peachbros.letsmerge.mission.model.domain;

import com.peachbros.letsmerge.user.model.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDateTime startDateTime;
    @Column(nullable = false)
    private LocalDateTime dueDateTime;
    @OneToMany
    private List<User> users = new ArrayList<>();

    public Mission() {
    }

    public Mission(String name, LocalDateTime startDateTime, LocalDateTime dueDateTime) {
        validateDateTime(startDateTime, dueDateTime);
        this.name = name;
        this.startDateTime = startDateTime;
        this.dueDateTime = dueDateTime;
    }

    private void validateDateTime(LocalDateTime startDateTime, LocalDateTime dueDateTime) {
        if (startDateTime.isAfter(dueDateTime)) {
            throw new IllegalArgumentException(String.format("시작 시간(%s)이 종료 시간(%s)보다 뒤에 있습니다.", startDateTime, dueDateTime));
        }
    }

    public void update(String name, LocalDateTime startDateTime, LocalDateTime dueDateTime) {
        if (name != null) {
            this.name = name;
        }
        if (startDateTime != null) {
            validateDateTime(startDateTime, this.dueDateTime);
            this.startDateTime = startDateTime;
        }
        if (dueDateTime != null) {
            validateDateTime(this.startDateTime, dueDateTime);
            this.dueDateTime = dueDateTime;
        }
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

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public List<User> getUsers() {
        return users;
    }
}