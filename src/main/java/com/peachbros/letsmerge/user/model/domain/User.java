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

    @OneToMany(mappedBy = "user")
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

    //미션 신청
    /* TODO: User와 AssignInfo는 일대다 양방향 매핑 형태. AssignInfo는 연관관계의 주인.
     * 그런데 AssignInfo의 생명주기는 항상 user 이후. user를 통해 생성/삭제됨.
     * new AssignInfo() 이 부분에 연관관계의 주인에 값을 입력했다고 볼 수 있는걸까?
     * 근데 얘를 이렇게 연관관계의 주인이 아닌 곳에서 만드는 행위는 올바를까?
     */
    public void assignMission(Mission mission) {
        AssignInfo assignInfo = new AssignInfo(this, mission, AssignStatus.ASSIGN);
        assignedMissions.add(assignInfo);
    }

    public void cancelMission(Mission mission) {
        AssignInfo assignInfo = assignedMissions.stream()
                .filter(assignedMission -> Objects.equals(assignedMission.getId(), mission.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchValueException("존재하지 않는 신청내역입니다."));

        assignInfo.setAssignStatus(AssignStatus.CANCEL);
        assignInfo.setUpdateDateTime(LocalDateTime.now());
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