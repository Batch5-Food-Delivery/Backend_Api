INSERT INTO users (firstname, lastname, username, email, password, enable, profile, available)
VALUES ('John', 'Doe', 'JohnDoe','john.doe@example.com', '$2y$10$mAWrRQZvBDYrRDyVurkA2.7i/kUipiIaHXdEngATSQXM7jPm2SOcm', TRUE, 'Profile description', FALSE);

-- Insert another user with different values
INSERT INTO users (firstname, lastname, username, email, password, enable, profile, available)
VALUES ('Jane', 'Smith', 'JaneSmith', 'jane.smith@example.com', '$2y$10$Hyp6vZP71uLcrEUkcGgJS.69YmO1vlXEhkGCL6SwrLtN4WwZeM9pK', FALSE, 'Another profile description', FALSE);

INSERT INTO role (id, name) VALUES (1, 'USER');
INSERT INTO role (id, name) VALUES (2, 'ADMIN');
INSERT INTO role (id, name) VALUES (3, 'DRIVER');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 3);

INSERT INTO food (id, name, picture, price, description, available) 
VALUES (13, 'Sample Product', 'sample-image.png', 19.99, 'This is a sample product description.', true);

-- Insert a new address with a specific ID
INSERT INTO address (Id, township, street, additional_details)
VALUES (1, 'Downtown', 'Main Street 123', 'Near the central park');

-- Insert another address with a different specific ID
INSERT INTO address (Id, township, street, additional_details)
VALUES (2, 'Uptown', 'Second Avenue 456', 'Close to the shopping mall');

-- Insert an Address record
INSERT INTO address (id, township, street, additional_details)
VALUES (3, 'Downtown', 'Main Street 123', 'Near the central park');

-- Insert a Restaruant record and reference the Address record with address_id = 1
INSERT INTO restaruant (id, name, profile, available, description, owner_id, address_id)
VALUES (2, 'The Gourmet Place', 'High-end dining', TRUE, 'A premium dining experience with exquisite dishes.', 1, 3);


-- Insert a new UserAddress with a specific ID
-- Ensure that user_id and address_id refer to existing records in the User and Address tables
INSERT INTO user_address (id, user_id, address_id)
VALUES (1, 1, 1);

-- Insert another UserAddress with a different specific ID
INSERT INTO user_address (id, user_id, address_id)
VALUES (2, 2, 2);

-- Insert an Order record and reference the Restaurant, User, and Address records
INSERT INTO orders (id, restaurant_id, customer_id, address_id, total, completed, started_at, completed_at)
VALUES (1, 2, 1, 1, 29.99, FALSE, '2024-08-12T10:15:30', NULL);

-- Insert an Order record and reference the Restaurant, User, and Address records
INSERT INTO orders (id, restaurant_id, customer_id, address_id, total, completed, started_at, completed_at)
VALUES (2, 2, 1, 1, 72.99, FALSE, '2024-08-12T10:15:30', NULL);

-- Insert an Order record and reference the Restaurant, User, and Address records
INSERT INTO orders (id, restaurant_id, customer_id, address_id, total, completed, started_at, completed_at)
VALUES (3, 2, 1, 1, 31.99, FALSE, '2024-08-12T10:15:30', NULL);

-- Insert an Order record and reference the Restaurant, User, and Address records
INSERT INTO orders (id, restaurant_id, customer_id, address_id, total, completed, started_at, completed_at)
VALUES (4, 2, 1, 1, 62.99, FALSE, '2024-08-12T10:15:30', NULL);

INSERT INTO delivery (
    restaurant_id, 
    restaurant_address_id, 
    order_id, 
    customer_id, 
    driver_id, 
    customer_address_id, 
    completed, 
    completed_at
) VALUES (
    2,  -- Assuming the restaurant with ID 1 exists
    3,  -- Assuming the address with ID 2 exists
    1,  -- Assuming the order with ID 1 exists
    1,  -- Assuming the customer (user) with ID 3 exists
    2,  -- Assuming the driver (user) with ID 4 exists
    2,  -- Assuming the customer address with ID 2 exists
    false,  -- Assuming the delivery is not completed initially
    NULL  -- completed_at is initially NULL
);

INSERT INTO food (id, name, picture, price, description, available) 
VALUES (15, 'Sample Product', 'sample-image.png', 19.99, 'This is a sample product description.', true);

