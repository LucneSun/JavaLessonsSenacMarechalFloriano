create database if not exists collectiblestore;
use collectiblestore;
create or replace table product (id int primary key auto_increment, name varchar(50), int quantity, type varchar(3), registerDate DATETIME DEFAULT CURRENT_TIMESTAMP);
