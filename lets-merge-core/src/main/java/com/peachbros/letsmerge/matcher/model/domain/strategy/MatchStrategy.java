package com.peachbros.letsmerge.matcher.model.domain.strategy;

import com.peachbros.letsmerge.user.model.domain.Groups;
import com.peachbros.letsmerge.user.model.domain.Users;

public interface MatchStrategy {
    Groups match(Users users);
}
