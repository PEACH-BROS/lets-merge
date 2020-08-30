package com.peachbros.letsmerge.mission.model.domain.assign;

import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.user.model.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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

    private LocalDateTime assignDateTime;

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

    public boolean matches(Mission mission) {
        return Objects.equals(this.getMission().getId(), mission.getId());
    }

    public boolean isAssigned() {
        return this.assignStatus == AssignStatus.ASSIGN;
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

    public void cancel() {
        this.assignStatus = AssignStatus.CANCEL;
        this.updateDateTime = LocalDateTime.now();
    }

    public LocalDateTime getAssignDateTime() {
        return assignDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }
}
