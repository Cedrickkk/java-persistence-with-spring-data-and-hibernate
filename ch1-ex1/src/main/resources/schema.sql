CREATE TABLE users (
    username VARCHAR(15) NOT NULL PRIMARY KEY,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE billing_details(
    account VARCHAR(15) NOT NULL PRIMARY KEY,
    bankname VARCHAR(255) NOT NULL,
    username VARCHAR(15) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);

# Adding User Defined Data Type (Address)

# CREATE TABLE users (
#     username VARCHAR(15) NOT NULL PRIMARY KEY,
#     address ADDRESS NOT NULL
# );

# UDT is not a common practice in legacy system and the current trend

# Stick with SQL vendors built-in types

CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(15) NOT NULL PRIMARY KEY,
    address_street VARCHAR(255) NOT NULL,
    address_zipcode VARCHAR(255) NOT NULL,
    address_city VARCHAR(255) NOT NULL
);

# Updated tables with surrogate keys

CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL PRIMARY KEY,
    username VARCHAR(15) NOT NULL,
    address_street VARCHAR(255) NOT NULL,
    address_zipcode VARCHAR(255) NOT NULL,
    address_city VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS billing_details(
    id BIGINT NOT NULL PRIMARY KEY,
    account VARCHAR(15) NOT NULL,
    bankname VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

# Creating a new table to link user to billing_details representing many-to-many relationship

CREATE TABLE IF NOT EXISTS user_billing_details(
    user_id BIGINT,
    billing_details_id BIGINT,
    PRIMARY KEY (user_id, billing_details_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (billing_details_id) REFERENCES billing_details (id)
);