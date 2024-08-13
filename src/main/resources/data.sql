INSERT INTO food (id, name, picture, price, description, available) 
VALUES (13, 'Sample Product', 'sample-image.png', 19.99, 'This is a sample product description.', true);

INSERT INTO users (id, firstname, lastname, email, password, enable, profile)
VALUES (1, 'John', 'Doe', 'john.doe@example.com', 'password123', TRUE, 'Profile description');

-- Insert another user with different values
INSERT INTO users (id, firstname, lastname, email, password, enable, profile)
VALUES (2, 'Jane', 'Smith', 'jane.smith@example.com', 'password456', FALSE, 'Another profile description');
