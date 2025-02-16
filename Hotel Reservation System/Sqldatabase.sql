CREATE DATABASE hotelmanagement;
use hotelmanagement;
create table rooms (
id int primary key ,
roomType varchar(30) ,
roomNumber INT unique,
price double,
name varchar(60) not null,
email varchar(60) not null,
address varchar(255) not null,
phone bigint 
);  select * from rooms;

