-- liquibase formatted sql

-- changeset Sergey:1657652891616-1
CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

-- changeset Sergey:1657652891616-2
CREATE TABLE audit_log
(
    id               BIGINT NOT NULL,
    method           VARCHAR(255),
    invoke_date_time TIMESTAMP,
    method_type      VARCHAR(255),
    CONSTRAINT pk_audit_log PRIMARY KEY (id)
);

-- changeset Sergey:1657652891616-3
CREATE TABLE author
(
    id         BIGINT NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    CONSTRAINT pk_author PRIMARY KEY (id)
);

-- changeset Sergey:1657652891616-4
CREATE TABLE author_isbn
(
    author_id BIGINT NOT NULL,
    isbn      VARCHAR(255)
);

-- changeset Sergey:1657652891616-5
ALTER TABLE author
    ADD CONSTRAINT uc_author_first_name UNIQUE (first_name, last_name);

-- changeset Sergey:1657652891616-6
ALTER TABLE author_isbn
    ADD CONSTRAINT fk_author_isbn_on_author FOREIGN KEY (author_id) REFERENCES author (id);

CREATE INDEX author_isbn_x ON author_isbn(isbn) ;

