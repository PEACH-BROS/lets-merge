package com.peachbros.letsmerge.user.model.domain;

import com.peachbros.letsmerge.mission.model.domain.Mission;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserGroup> users;

    @ManyToOne
    @JoinColumn(name = "MISSION_ID")
    private Mission mission;

    protected Group() {
    }

    public Group(List<UserGroup> users, Mission mission) {
        this.users = users;
        this.mission = mission;
    }

    public Group(List<User> users) {
        this.users = users.stream()
                .map(user -> new UserGroup(user, this))
                .collect(Collectors.toList());
    }

    public void add(User user) {
        this.users = new ArrayList<>(users);
        this.users.add(new UserGroup(user, this));
    }

    public boolean hasUser(Long userId) {
        //TODO: User.isSameUserWith로 변경하기
        Optional<UserGroup> first = users.stream().filter(user -> user.getUser().getId().equals(userId)).findFirst();
        return first.isPresent();
    }

    public int size() {
        return users.size();
    }
}