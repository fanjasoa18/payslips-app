CREATE TABLE employees (
    id serial PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50)NOT NULL,
    date_of_birth DATE NOT NULL ,
    employee_matricule VARCHAR(20) NOT NULL UNIQUE,
    image varchar,
    sex VARCHAR NOT NULL ,
    phone_numbers VARCHAR(200) NOT NULL ,
    address VARCHAR NOT NULL ,
    email_perso VARCHAR(100) NOT NULL ,
    email_pro VARCHAR(100) NOT NULL ,
    cin_number bigint NOT NULL ,
    delivrance_date DATE,
    delivrance_place VARCHAR(100),
    function VARCHAR NOT NULL ,
    number_of_kids int NOT NULL ,
    hire_date DATE NOT NULL ,
    departure_date DATE NOT NULL ,
    csp VARCHAR NOT NULL ,
    cnaps VARCHAR(100) NOT NULL
);