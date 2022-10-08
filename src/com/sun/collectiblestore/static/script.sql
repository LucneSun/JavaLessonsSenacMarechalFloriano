create database if not exists collectiblestore;
use collectiblestore;
create or replace table product (id int primary key auto_increment, name varchar(50), quantity int, price double(4, 2), type varchar(3), registerDate DATETIME DEFAULT CURRENT_TIMESTAMP);
create or replace table storedcharacter (id int primary key auto_increment, name varchar(50), inventory varchar(39));
