-- =========================================
-- Table: address_entity
-- =========================================
CREATE TABLE address_entity (
    id BIGSERIAL PRIMARY KEY,
    city VARCHAR(255) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    street VARCHAR(255) NOT NULL,
    house_number VARCHAR(20) NOT NULL
);

-- =========================================
-- Table: employee_entity
-- =========================================
CREATE TABLE employee_entity (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    second_name VARCHAR(255),
    last_name VARCHAR(255) NOT NULL,
    address_id BIGINT UNIQUE REFERENCES address_entity(id)
);

-- =========================================
-- Table: assignment_entity
-- =========================================
CREATE TABLE assignment_entity (
    id BIGSERIAL PRIMARY KEY,
    address_id BIGINT UNIQUE REFERENCES address_entity(id),
    status VARCHAR(50),
    type VARCHAR(50)
);

-- =========================================
-- Table: employee_assignment (Many-to-Many)
-- =========================================
CREATE TABLE employee_assignment (
    employee_id BIGINT REFERENCES employee_entity(id),
    assignment_id BIGINT REFERENCES assignment_entity(id),
    PRIMARY KEY(employee_id, assignment_id)
);

-- =========================================
-- Table: note_entity
-- =========================================
CREATE TABLE note_entity (
    id BIGSERIAL PRIMARY KEY,
    content VARCHAR(2000) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    author_id BIGINT NOT NULL REFERENCES employee_entity(id),
    assignment_id BIGINT NOT NULL REFERENCES assignment_entity(id)
);

-- =========================================
-- Insert sample addresses
-- =========================================
INSERT INTO address_entity (city, postal_code, street, house_number)
VALUES ('Warsaw', '00-001', 'Main Street', '1'),
       ('Krakow', '30-002', 'Market Street', '10');

-- =========================================
-- Insert sample employees
-- =========================================
INSERT INTO employee_entity (first_name, second_name, last_name, address_id)
VALUES ('Jan', NULL, 'Kowalski', 1),
       ('Anna', 'Maria', 'Nowak', 2);

-- =========================================
-- Insert sample assignments
-- =========================================
INSERT INTO assignment_entity (address_id, status, type)
VALUES (1, 'NEW', 'TASK'),
       (2, 'IN_PROGRESS', 'PROJECT');

-- =========================================
-- Link employees to assignments
-- =========================================
INSERT INTO employee_assignment (employee_id, assignment_id)
VALUES (1, 1),
       (2, 2);

-- =========================================
-- Insert sample notes
-- =========================================
INSERT INTO note_entity (content, author_id, assignment_id)
VALUES ('Pierwsza notatka', 1, 1),
       ('Druga notatka', 2, 2);
