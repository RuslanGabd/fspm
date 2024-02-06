package org.ruslan.user.userDao;

import org.ruslan.core.dao.RepositoryBase;
import org.ruslan.user.userEntity.UserProfile;

public class UserProfileRepository extends RepositoryBase<Long, UserProfile> {
    public UserProfileRepository() {
        super(UserProfile.class);
    }

}
