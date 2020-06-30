package com.peachbros.letsmerge.mission.model.domain.assign;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.user.model.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AssignInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "MISSION_ID")
    private Mission mission;

    @Enumerated(value = EnumType.STRING)
    private AssignStatus assignStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime assignDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateDateTime;

    protected AssignInfo() {
    }

    public AssignInfo(User user, Mission mission, AssignStatus assignStatus) {
        this.user = user;
        this.mission = mission;
        this.assignStatus = assignStatus;
        if (this.assignDateTime == null) {
            this.assignDateTime = LocalDateTime.now();
        }
        this.updateDateTime = LocalDateTime.now();
    }

    public void setAssignStatus(AssignStatus assignStatus) {
        this.assignStatus = assignStatus;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Mission getMission() {
        return mission;
    }

    public AssignStatus getAssignStatus() {
        return assignStatus;
    }

    public LocalDateTime getAssignDateTime() {
        return assignDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }
}
