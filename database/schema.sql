-- schema.sql
-- Description: The database schemas for Aegis Pass

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    hashed_password TEXT NOT NULL,
    salt_for_kdf TEXT NOT NULL
);

CREATE TABLE password_manager_data (
    entry_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    title VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password TEXT NOT NULL,
    two_factor_secret_key VARCHAR(255),
    url VARCHAR(255),
    icon_url TEXT,
    date VARCHAR(255),
    notes TEXT,
    CONSTRAINT unique_entry_user UNIQUE (user_id, username, title)
);