package com.peachbros.letsmerge.mission.model.domain;

import com.peachbros.letsmerge.user.model.domain.User;
import com.peachbros.letsmerge.user.model.domain.Users;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.peachbros.letsmerge.mission.model.domain.Mission.MISSION_DATE_FORMAT;

public class MissionManager {
    private static final Map<Mission, Users> applicants = new HashMap<>();

    public static void applyIfApplicable(Mission mission, User user, String applyTime) {
        validateApplicable(mission, user, LocalDateTime.parse(applyTime, MISSION_DATE_FORMAT));
        apply(mission, user);
    }

    private static void apply(Mission mission, User user) {
        Users users = applicants.computeIfAbsent(mission, key -> new Users());
        users.add(user);
        applicants.replace(mission, users);
    }

    private static void validateApplicable(Mission mission, User user, LocalDateTime parsedApplyTime) {
        Users users = applicants.get(mission);

        if (users.contains(user)) {
            throw new IllegalArgumentException("이미 신청되어 있습니다?");
        }
        if (mission.isNotActive(parsedApplyTime)) {
            throw new IllegalArgumentException("미션 신청 기간이 지났습니다.");
        }
    }

    public static Map<Mission, Users> getApplicants() {
        return applicants;
    }
}
