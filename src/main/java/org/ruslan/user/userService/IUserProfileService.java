package org.ruslan.user.userService;

import org.ruslan.user.userEntity.UserProfile;

import java.util.List;
import java.util.Optional;

public interface IUserProfileService {
    public boolean userProfileExists(String email);

    public Optional<UserProfile> saveUserProfile(UserProfile userProfile);

    UserProfile getUserProfile(Long id);

    public List<UserProfile> getList();

    boolean deleteUserProfile(Long userProfileId);

}
