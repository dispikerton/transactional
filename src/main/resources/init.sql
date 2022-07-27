CREATE TABLE customer (
    id    BIGSERIAL PRIMARY KEY,
    fio   TEXT    NOT NULL,
    money NUMERIC NOT NULL
);

CREATE TABLE purchase (
    id    BIGSERIAL PRIMARY KEY,
    info  TEXT    NOT NULL,
    price numeric NOT NULL
);