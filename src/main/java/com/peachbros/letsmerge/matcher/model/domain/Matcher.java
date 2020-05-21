package com.peachbros.letsmerge.matcher.model.domain;

import com.peachbros.letsmerge.matcher.model.domain.strategy.MatchStrategy;
import com.peachbros.letsmerge.user.model.domain.Groups;
import com.peachbros.letsmerge.user.model.domain.Users;

public class Matcher {
    public static Groups match(Users users, MatchStrategy strategy) {
        users.shuffle();
        return strategy.match(users);
    }
}