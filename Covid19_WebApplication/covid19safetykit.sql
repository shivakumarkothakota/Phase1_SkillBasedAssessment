-- This file contains the db script for Covid19 Safety Kit Database
DROP DATABASE covid19SafetyKit;

CREATE DATABASE covid19SafetyKit;

USE covid19SafetyKit;

CREATE TABLE productsRepository(
	productId int primary key,
	productName varchar(40) not null,
	costPerUnit decimal not null,
	productDescription varchar(40) not null
);

INSERT INTO productsRepository VALUES
(1,"Sanitizer",20,"Gel"),
(2,"Mask",40,"Cloth Based"),
(3,"PPE Kit",1500,"Full Body "),
(4,"OxydoMeter",1800,"Finger Based"),
(5,"Thermometer",200,"Digital");


CREATE TABLE covidKit(
	id int PRIMARY KEY auto_increment,
	kitid int not null,
	product varchar(50) not null,
	quantity int not null,
	price int not null
	
);
