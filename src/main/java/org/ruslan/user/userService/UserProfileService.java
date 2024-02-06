package org.ruslan.user.userService;

import org.ruslan.user.userEntity.UserProfile;

import java.util.List;
import java.util.Optional;

public class UserProfileService implements IUserProfileService {
    @Override
    public boolean userProfileExists(String email) {
        return false;
    }

    @Override
    public Optional<UserProfile> saveUserProfile(UserProfile userProfile) {
        return Optional.empty();
    }

    @Override
    public UserProfile getUserProfile(Long id) {
        return null;
    }

    @Override
    public List<UserProfile> getList() {
        return null;
    }

    @Override
    public boolean deleteUserProfile(Long userProfileId) {
        return false;
    }
}
