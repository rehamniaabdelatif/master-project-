CREATE DATABASE master;
SHOW DATABASE;

USE master;

CREATE TABLE User (
    Personid int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    UserName varchar(10) NOT NULL,
    Pasword varchar(20) NOT NULL 
);
SHOW TABLES;
DESC User;
