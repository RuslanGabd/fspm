DROP TABLE IF EXISTS userprofile, username, role, user_roles;

CREATE TABLE user
(
    id       BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(25)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    role_id  BIGINT  UNSIGNED     NOT NULL,
    enabled  TINYINT(1) DEFAULT 1,
        CONSTRAINT `Constr_user_role_id_fk`
        FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = INNODB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;
CREATE UNIQUE INDEX login ON user (username);


CREATE TABLE role
(
    id        BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(15) NOT NULL
) ENGINE = INNODB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;
CREATE UNIQUE INDEX role ON role (role_name);

# CREATE TABLE user_role
# (
#     `username_id` BIGINT UNSIGNED NOT NULL,
#     `role_id`     BIGINT UNSIGNED NOT NULL,
#     PRIMARY KEY (`username_id`, `role_id`),
#     CONSTRAINT `Constr_usersroles_user_fk`
#         FOREIGN KEY (`username_id`) REFERENCES `username` (`id`)
#             ON DELETE CASCADE ON UPDATE CASCADE,
#     CONSTRAINT `Constr_usersroles_role_fk`
#         FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
#             ON DELETE CASCADE ON UPDATE CASCADE
# ) ENGINE = INNODB
#   CHARACTER SET utf8mb4
#   COLLATE utf8mb4_general_ci;

CREATE TABLE user_profile
(
    id            BIGINT UNSIGNED AUTO_INCREMENT,
    user_id       BIGINT UNSIGNED,
    email         VARCHAR(100) UNIQUE NOT NULL,
    full_name     VARCHAR(100),
    date_of_birth DATE,
    PRIMARY KEY (`id`),
    CONSTRAINT `Constr_user_profile_user_id_fk`
        FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
            ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = INNODB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

#
# INSERT INTO users (username, password)
# VALUES ('admin_name', '$2y$10$rZF.PX./ojG041lxwYXJjekb40TgjhV42xEJ2ZRs8FiW4oy2qW9Ee'),
#        ('user_name', '$2y$10$LgbojSqGUf.RrGzztUFDm.Z4q.cEA423OQQu.4sptnSr05G1eRW9e');
#
# INSERT INTO role (role_name)
# VALUES ('ADMIN'), ('USER');
#
# INSERT INTO users_roles (id_user, id_user)
# VALUES ('1', '1'), ('1', '2'), ('2', '2');
