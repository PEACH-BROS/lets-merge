package com.peachbros.letsmerge.user.model.domain;

import com.peachbros.letsmerge.mission.model.domain.Mission;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table(name = "GroupResult")
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

    public Group(List<User> users) {
        this.users = users.stream()
                .map(user -> new UserGroup(user, this))
                .collect(Collectors.toList());
    }

    public void addUser(User user) {
        this.users = new ArrayList<>(users);
        this.users.add(new UserGroup(user, this));
    }

    public void addMission(Mission mission) {
        this.mission = mission;
    }

    public boolean hasUser(Long userId) {
        Optional<UserGroup> first = users.stream().filter(user -> user.isSameUserWith(userId)).findFirst();
        return first.isPresent();
    }

    public int size() {
        return users.size();
    }

    public List<User> getUsers() {
        return users.stream()
                .map(UserGroup::getUser)
                .collect(Collectors.toList());
    }
}