package com.peachbros.letsmerge.mission.model.domain;

import com.peachbros.letsmerge.user.model.domain.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.peachbros.letsmerge.mission.model.domain.Mission.MISSION_DATE_FORMAT;

public class MissionManager {
    private static final Map<Mission, List<User>> applicants = new HashMap<>();

    public static boolean apply(Mission mission, User user, String applyTime) {
        List<User> users = applicants.computeIfAbsent(mission, key -> new ArrayList<>());
        LocalDateTime parsedApplyTime = LocalDateTime.parse(applyTime, DateTimeFormatter.ofPattern(MISSION_DATE_FORMAT));

        if (users.contains(user) || mission.isNotActive(parsedApplyTime)) {
            return false;
        }

        users.add(user);
        applicants.replace(mission, users);
        return true;
    }

}
