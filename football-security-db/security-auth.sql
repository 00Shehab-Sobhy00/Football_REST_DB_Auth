DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS members;

CREATE TABLE members
(
    user_id varchar(50) NOT NULL,
    pw      char(68)    NOT NULL,
    active  boolean     NOT NULL,
    PRIMARY KEY (user_id)
);

INSERT INTO members (user_id, pw, active)
VALUES ('Admin', '{noop}a123', true);

CREATE TABLE roles
(
    user_id varchar(50) NOT NULL,
    role    varchar(50) NOT NULL,
    CONSTRAINT roles_pk PRIMARY KEY (user_id, role),
    CONSTRAINT fk_roles_members FOREIGN KEY (user_id) REFERENCES members (user_id)
);

INSERT INTO roles (user_id, role)
VALUES ('Admin', 'ROLE_ADMIN');