
INSERT INTO user (username, password)
VALUES ('admin_name', '$2a$12$WJYoloHif17.G1Hibd.aFO0BC3vJFZXOOsd4KdG60U1fc091fz9Tq'),
       ('user_name', '$2a$12$WJYoloHif17.G1Hibd.aFO0BC3vJFZXOOsd4KdG60U1fc091fz9Tq');

INSERT INTO role (role_name)
VALUES ('ADMIN'), ('USER');

INSERT INTO user_role (user_id, role_id)
VALUES ('1', '1'), ('1', '2'), ('2', '2');

INSERT INTO user_profile (user_id, email,full_name,date_of_birth)
VALUES ('1', 'example1@gmail.com','Michael Jeffrey Jordan','1963-02-17'),
       ('2', 'example2@gmail.com','Timothy Theodore Duncan','1973-04-25');
