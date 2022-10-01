CREATE TABLE post(
   id SERIAL PRIMARY KEY,
   name TEXT,
   description TEXT,
   created TIMESTAMP,
   visible BOOLEAN,
   city_id INT
);

CREATE TABLE candidate(
   id SERIAL PRIMARY KEY,
   name TEXT,
   description TEXT,
   created TIMESTAMP,
   visible BOOLEAN,
   photo BYTEA,
   city_id INT
);

CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  email VARCHAR,
  password TEXT
);

ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email);