CREATE TABLE users (
id SERIAL PRIMARY KEY,
email VARCHAR(255) NOT NULL UNIQUE,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(100) NOT NULL,
password VARCHAR(255) NOT NULL,
role VARCHAR(20) DEFAULT 'USER' NOT NULL,
status VARCHAR(20) DEFAULT 'ACTIVE' NOT NULL

);

INSERT INTO users(email, first_name, last_name, password, role, status) VALUES 
('admin@mail.com', 'Kevin',  'Spacey', '$2a$12$fLqB.eKfRFL4WxNkDrgPxurBTLNO5vwGja4HH438yPm8pY8UDLSny', 'ADMIN', 'ACTIVE'),
('user@mail.com', 'Morgan', 'Freeman', '$2a$12$2mdGlK7OGEXVATgAXdD8R.XhVVy8NOYe1NiFvkw9vSMK3BbkeNe6C', 'USER', 'ACTIVE');