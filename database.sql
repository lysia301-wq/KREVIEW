CREATE DATABASE IF NOT EXISTS trafficdb;
USE trafficdb;

CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    roll_no VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('student', 'management') NOT NULL,
    gender ENUM('male', 'female') NOT NULL,
    room_no VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS menu (
    menu_id INT AUTO_INCREMENT PRIMARY KEY,
    menu_date DATE NOT NULL,
    meal_type ENUM('breakfast','lunch','snacks','dinner') 
        NOT NULL,
    item_name VARCHAR(100) NOT NULL,
    created_by INT,
    FOREIGN KEY (created_by) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS ratings (
    rating_id INT AUTO_INCREMENT PRIMARY KEY,
    menu_id INT NOT NULL,
    student_id INT NOT NULL,
    stars INT CHECK (stars BETWEEN 1 AND 5),
    feedback TEXT,
    rated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (menu_id) REFERENCES menu(menu_id),
    FOREIGN KEY (student_id) REFERENCES users(user_id),
    UNIQUE(menu_id, student_id)
);

CREATE TABLE IF NOT EXISTS waste_tracker (
    waste_id INT AUTO_INCREMENT PRIMARY KEY,
    waste_date DATE NOT NULL,
    meal_type ENUM('breakfast','lunch','snacks','dinner') 
        NOT NULL,
    total_prepared_kg DECIMAL(6,2),
    waste_kg DECIMAL(6,2),
    entered_by INT,
    FOREIGN KEY (entered_by) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS suggestions (
    suggestion_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    suggested_item VARCHAR(100) NOT NULL,
    meal_type ENUM('breakfast','lunch','snacks','dinner'),
    description TEXT,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('pending','considered','rejected') 
        DEFAULT 'pending',
    FOREIGN KEY (student_id) REFERENCES users(user_id)
);

-- Test data
INSERT IGNORE INTO users 
    (name, roll_no, password, role, gender, room_no)
VALUES 
    ('Test Student','21CSE001','1234','student','female','A101'),
    ('Mess Manager','MESS001','1234','management','male',NULL);
