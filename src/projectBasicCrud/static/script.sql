create database if not exists db01;
use db01;
create or replace table student (id int primary key auto_increment, registration int not null unique, name varchar(150) not null, email varchar(150) not null unique, sex varchar(1) not null);