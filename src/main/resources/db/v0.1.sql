CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    iconId integer
);

CREATE TABLE expense (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    comment VARCHAR(255),
    amount integer NOT NULL,
    date DATE NOT NULL,
    category_id integer,
    user_id integer
);

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    icon_id integer,
    user_id integer,
    hidden boolean DEFAULT FALSE
);
