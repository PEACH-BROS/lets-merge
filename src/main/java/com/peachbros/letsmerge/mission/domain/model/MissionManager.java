package com.peachbros.letsmerge.mission.domain.model;

import com.peachbros.letsmerge.user.model.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissionManager {
    private static final Map<Mission, List<User>> applicants = new HashMap<>();

    public static boolean apply(Mission mission, User user) {
        List<User> users = applicants.computeIfAbsent(mission, key -> new ArrayList<>());

        if (users.contains(user)) {
            return false;
        }

        users.add(user);
        applicants.replace(mission, users);
        return true;
    }

}
