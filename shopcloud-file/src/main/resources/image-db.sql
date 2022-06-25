drop database if exists shopcloud_fileservice;

create database shopcloud_fileservice character set utf8;
use shopcloud_fileservice;

create table image(
    id int auto_increment primary key ,
    url varchar(128) not null unique ,
    prefix varchar(20) null ,
    introduction varchar(20) null
);
