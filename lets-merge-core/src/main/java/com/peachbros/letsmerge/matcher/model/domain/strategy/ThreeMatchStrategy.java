package com.peachbros.letsmerge.matcher.model.domain.strategy;

import com.peachbros.letsmerge.user.model.domain.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ThreeMatchStrategy implements MatchStrategy {
    private static final int DEFAULT_GROUP_SIZE = 3;

    private static Groups divideUsersByDefaultGroupSize(Users users) {
        List<Group> groups = IntStream.range(0, users.size() / DEFAULT_GROUP_SIZE)
                .map(i -> i * DEFAULT_GROUP_SIZE)
                .mapToObj(i -> new Group(Arrays.asList(users.get(i), users.get(i + 1), users.get(i + 2))))
                .collect(Collectors.toList());
        return new Groups(groups);
    }

    @Override
    public Groups match(Users users) {
        int usersSize = users.size();
        Groups matchedGroup = divideUsersByDefaultGroupSize(users);

        if (usersSize % DEFAULT_GROUP_SIZE == 1) {
            matchedGroup.addUserInFirstGroup(users.get(usersSize - 1));
            return matchedGroup;
        }

        if (usersSize % DEFAULT_GROUP_SIZE == 2) {
            Group modGroup = new Group(Arrays.asList(users.get(usersSize - 1), users.get(usersSize - 2)));
            matchedGroup.add(modGroup);
        }
        return matchedGroup;
    }
}
