package com.peachbros.letsmerge.matcher.model.domain;

import com.peachbros.letsmerge.user.model.domain.Group;
import com.peachbros.letsmerge.user.model.domain.Groups;
import com.peachbros.letsmerge.user.model.domain.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matcher {

    private static final int DEFAULT_GROUP_SIZE = 3;

    public static Groups match(List<User> users) {
        Collections.shuffle(users);
        return calculate(users);
    }

    private static Groups calculate(List<User> users) {
        int usersSize = users.size();
        Groups matchedGroup = divideUsersByDefaultGroupSize(users);

        if (usersSize % DEFAULT_GROUP_SIZE == 1) {
            Group firstGroup = matchedGroup.getGroups().get(0);
            firstGroup.add(users.get(usersSize - 1));
            return matchedGroup;
        }

        if (usersSize % DEFAULT_GROUP_SIZE == 2) {
            Group modGroup = new Group(Arrays.asList(users.get(usersSize - 1), users.get(usersSize - 2)));
            matchedGroup.add(modGroup);
        }
        return matchedGroup;
    }

    private static Groups divideUsersByDefaultGroupSize(List<User> users) {
        List<Group> groups = IntStream.range(0, users.size() / DEFAULT_GROUP_SIZE)
                .map(i -> i * DEFAULT_GROUP_SIZE)
                .mapToObj(i -> new Group(Arrays.asList(users.get(i), users.get(i + 1), users.get(i + 2))))
                .collect(Collectors.toList());
        return new Groups(groups);
    }
}