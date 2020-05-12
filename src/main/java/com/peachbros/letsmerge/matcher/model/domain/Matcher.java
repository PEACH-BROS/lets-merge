package com.peachbros.letsmerge.matcher.model.domain;

import com.peachbros.letsmerge.user.model.domain.Group;
import com.peachbros.letsmerge.user.model.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Matcher {

    public static List<Group> match(List<User> users) {
        Collections.shuffle(users);
        return calculate(users);
    }

    private static List<Group> calculate(List<User> users) {
        List<Group> matchedGroup = new ArrayList<>();
        divideThree(users, matchedGroup);
        ifTwoRemains(users, matchedGroup);
        ifOneRemains(users, matchedGroup);
        return matchedGroup;
    }

    private static void divideThree(List<User> users, List<Group> matchedGroup) {
        List<User> small = new ArrayList<>();
        int j = 0;
        for (User user : users) {
            small.add(user);
            j++;
            if (j == 3) {
                j = 0;
                matchedGroup.add(new Group(small));
                small = new ArrayList<>();
            }
        }
    }

    private static void ifTwoRemains(List<User> users, List<Group> matchedGroup) {
        List<User> small = new ArrayList<>();
        if (users.size() % 3 == 2) {
            small.add(users.get(users.size() - 2));
            small.add(users.get(users.size() - 1));
            matchedGroup.add(new Group(small));
        }
    }

    private static void ifOneRemains(List<User> users, List<Group> matchedGroup) {
        if (users.size() % 3 == 1) {
            matchedGroup.get(0).add(users.get(users.size() - 1));
        }
    }
}