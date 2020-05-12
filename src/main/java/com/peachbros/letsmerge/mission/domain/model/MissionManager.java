package com.peachbros.letsmerge.mission.domain.model;

import com.peachbros.letsmerge.user.model.domain.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissionManager {
    private static final Map<Mission, List<User>> applicants = new HashMap<>();

    public static boolean apply(Mission mission, User user, String applyTime) {
        List<User> users = applicants.computeIfAbsent(mission, key -> new ArrayList<>());
        LocalDateTime parsedApplyTime = LocalDateTime.parse(applyTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        if (users.contains(user) || mission.isNotActive(parsedApplyTime)) {
            return false;
        }

        users.add(user);
        applicants.replace(mission, users);
        return true;
    }

}
