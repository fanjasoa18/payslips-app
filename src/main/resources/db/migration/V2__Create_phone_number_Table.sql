CREATE TABLE phone_number
(
    id    serial PRIMARY KEY,
    phone_numbers VARCHAR(200) NOT NULL,
    employee_id INT REFERENCES employees(id),
    UNIQUE (phone_numbers),
    CHECK (phone_numbers<> '')
)