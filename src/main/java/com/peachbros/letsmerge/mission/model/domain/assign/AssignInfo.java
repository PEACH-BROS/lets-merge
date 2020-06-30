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
}
