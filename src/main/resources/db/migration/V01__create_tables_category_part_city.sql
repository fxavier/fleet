CREATE TABLE category(
id SERIAL,
name VARCHAR(250) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE part(
id BIGSERIAL,
name VARCHAR(200) NOT NULL,
description TEXT,
stock INTEGER default 0,
price NUMERIC(10, 2) NOT NULL,
category_id BIGINT NOT NULL,
PRIMARY KEY(id),
CONSTRAINT fk_part_category
  FOREIGN KEY(category_id) REFERENCES category(id)
);


CREATE TABLE province(
province_id SERIAL,
province_name VARCHAR(100) NOT NULL,
PRIMARY KEY(province_id)
);

CREATE TABLE city(
city_id SERIAL,
city_name VARCHAR(100) NOT NULL,
province_id INTEGER NOT NULL,
PRIMARY KEY(city_id),
CONSTRAINT fk_province_city
  FOREIGN KEY(province_id) REFERENCES province(province_id)
);

