CREATE DATABASE IF NOT EXISTS SpringDatabase;

USE SpringDatabase;

DROP TABLE IF EXISTS ProductOrder;
DROP TABLE IF EXISTS cart_entity;
DROP TABLE IF EXISTS product_entity;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS company_entity;
DROP TABLE IF EXISTS bank_account_entity;

INSERT INTO bank_account_entity (bank_balance, bank_code, bank_date, cvv) VALUES(1200,1234,'04/25',12);

INSERT INTO company_entity(name)
VALUES('logitech');

INSERT INTO user (name, surname, username, email, birth_date, gender, password, is_Admin, is_Moderator, is_Seller)
VALUES ('Admin', 'Admin', 'AdminUser', 'admin@example.com', '1980-05-15', 'Male', '$2a$10$ZqiOG5yYC.NT4wTzctHjv.66AUBVkDjR/hPMkP4H2FiN01Pyk9ePO', 1, 1, 1);
INSERT INTO user (name, surname, username, email, birth_date, gender, password, is_Admin, is_Moderator, is_Seller)
VALUES ('Moderator', 'Moderator', 'ModUser', 'mod@example.com', '1980-05-15', 'Male', '$2a$10$rxMCN/KHgEH2awQ5..87H.u8NcFkqBYMrCU.IkIri2KwAcR0kN2Vm', 0, 1, 0);
INSERT INTO user (name, surname, username, email, birth_date, gender, password, is_Admin, is_Moderator, is_Seller)
VALUES ('User', 'User', 'User', 'user@example.com', '1980-05-15', 'Male', '$2a$10$zh.cQrTCZcTgt4jWtcV3UO9eqbaq1eH8/K7HHiwJyxitLXUxYIfkq', 0, 0, 0);