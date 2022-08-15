CREATE TABLE tick (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    date DATE,
    description VARCHAR(1000),
    comp BOOLEAN NOT NULL
);