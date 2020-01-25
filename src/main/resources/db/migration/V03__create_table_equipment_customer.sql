CREATE TABLE equipment_type(
id SERIAL NOT NULL,
name VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE equipment(
id BIGSERIAL NOT NULL,
make VARCHAR(50),
number_plate VARCHAR(50),
model VARCHAR(50),
year BIGINT,
chassi VARCHAR(100),
engine VARCHAR(100),
description TEXT,
type_id BIGINT,
PRIMARY KEY(id),
CONSTRAINT fk_equipment_type
  FOREIGN KEY(type_id) REFERENCES equipment_type(id)
);

CREATE TABLE customer(
id BIGSERIAL NOT NULL,
name VARCHAR(100) NOT NULL,
contact_person VARCHAR(100),
address VARCHAR(250) NOT NULL,
phone1 VARCHAR(50) NOT NULL,
phone2 VARCHAR(50),
fax VARCHAR(50),
email VARCHAR(100) NOT NULL,
website VARCHAR(50),
pobox VARCHAR(20),
status VARCHAR(20) NOT NULL,
city_id BIGINT NOT NULL,
PRIMARY KEY(id),
CONSTRAINT fk_customer_city
  FOREIGN KEY(city_id) REFERENCES city(city_id)
);