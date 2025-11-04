CREATE TABLE Client (
    id INT PRIMARY KEY,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    license_number VARCHAR(9) NOT NULL
);