INSERT INTO users (firstname, lastname, username, email, password, enable, profile, available)
VALUES ('John', 'Doe', 'JohnDoe','john.doe@example.com', '$2y$10$mAWrRQZvBDYrRDyVurkA2.7i/kUipiIaHXdEngATSQXM7jPm2SOcm', TRUE, 'Profile description', FALSE);

INSERT INTO role (id, name) VALUES (1, 'USER');
INSERT INTO role (id, name) VALUES (2, 'ADMIN');
INSERT INTO role (id, name) VALUES (3, 'DRIVER');