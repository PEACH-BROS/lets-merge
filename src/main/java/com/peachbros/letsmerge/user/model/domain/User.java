package com.peachbros.letsmerge.user.model.domain;

import com.peachbros.letsmerge.core.exception.NoSuchValueException;
import com.peachbros.letsmerge.mission.model.domain.Mission;
import com.peachbros.letsmerge.mission.model.domain.assign.AssignInfo;
import com.peachbros.letsmerge.mission.model.domain.assign.AssignStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class User {
    public static final String NO_PICTURE = "NO PICTURE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssignInfo> assignedMissions = new ArrayList<>();

    protected User() {
    }

    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public static User ofRoleUser(String name, String email) {
        return new User(name, email, NO_PICTURE, Role.USER);
    }

    public User update(String email, String picture) {
        this.email = email;
        this.picture = picture;

        return this;
    }

    public void addAssignInfo(AssignInfo assignInfo) {
        this.assignedMissions.add(assignInfo);
    }

    public void cancelMission(Mission mission) {
        AssignInfo assignInfo = assignedMissions.stream()
                .filter(assignedMission -> Objects.equals(assignedMission.getId(), mission.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchValueException("존재하지 않는 신청내역입니다."));

        //User에서는 assignInfo가 업데이트되고,
        assignInfo.setAssignStatus(AssignStatus.CANCEL);
        assignInfo.setUpdateDateTime(LocalDateTime.now());

        //Mission에서는 assignInfo가 지워진다.
        mission.removeAssignInfo(assignInfo);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

    public Role getRole() {
        return role;
    }

    public String getRoleKey() {
        return role.getKey();
    }

    public List<Mission> getAssignedMissions() {
        return assignedMissions.stream()
                .filter(assignInfo -> Objects.equals(assignInfo.getAssignStatus(), AssignStatus.ASSIGN))
                .map(AssignInfo::getMission)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

}