package com.forthenew.springbook.user.service;

import com.forthenew.springbook.user.User;

public interface UserLevelUpgradePolicy {
	boolean canUpgradeLevel(User user);
	void upgradeLevel(User user);
}
