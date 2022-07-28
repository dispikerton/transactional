CREATE TABLE customer (
    id    BIGSERIAL PRIMARY KEY,
    fio   TEXT    NOT NULL,
    money NUMERIC NOT NULL
);

CREATE TABLE purchase (
    id          BIGSERIAL PRIMARY KEY,
    customer_id BIGINT  NOT NULL,
    price       numeric NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);