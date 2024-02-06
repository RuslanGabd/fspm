DROP TABLE IF EXISTS user_profile, user, role, user_role;

CREATE TABLE user
(
    id       INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(25)  NOT NULL,
    password VARCHAR(100) NOT NULL
#   //  enabled  TINYINT(1) DEFAULT 1
) ENGINE = INNODB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;
CREATE UNIQUE INDEX login ON user (username);


CREATE TABLE role
(
    id        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(15) NOT NULL
) ENGINE = INNODB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;
CREATE UNIQUE INDEX role ON role (role_name);

CREATE TABLE user_role
(
    `user_id` INT UNSIGNED NOT NULL,
    `role_id`     INT UNSIGNED NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    CONSTRAINT `Constr_usersroles_user_fk`
        FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
            ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `Constr_usersroles_role_fk`
        FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
            ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = INNODB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

CREATE TABLE user_profile
(
    id            INT UNSIGNED AUTO_INCREMENT,
    user_id       INT UNSIGNED,
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

