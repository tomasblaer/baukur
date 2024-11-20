CREATE TABLE default_category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    icon_id integer
);

ALTER TABLE category ADD COLUMN default_category_id integer;

INSERT INTO default_category (name) VALUES ('Car Payments');
INSERT INTO default_category (name) VALUES ('Groceries');
INSERT INTO default_category (name) VALUES ('Rent');
INSERT INTO default_category (name) VALUES ('Subscriptions');
INSERT INTO default_category (name) VALUES ('Entertainment');
INSERT INTO default_category (name) VALUES ('Utilities & Bills');
INSERT INTO default_category (name) VALUES ('Hobbies');

CREATE TABLE icons (
    id SERIAL PRIMARY KEY,
    data blob,
);
