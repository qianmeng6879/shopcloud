drop database if exists shopcloud_userservice;

create database shopcloud_userservice character set utf8;
use shopcloud_userservice;

create table user
(
    id          int auto_increment,
    username    varchar(10)  not null unique,
    password    varchar(128) not null,
    balance     decimal(10, 2) default 0.00,
    staff       tinyint(1)     default 0,
    picture     varchar(128)   default 'user/default.jpg',
    create_time datetime     not null,
    update_time datetime,
    is_delete   tinyint(1)     default 0,
    primary key (id)
);

insert into user (username, password, balance, create_time)
values ('zero', 'hello', 120, now()),
       ('zhangsan', 'admin', 58, now()),
       ('lisi', 'admin', 96, now());


create table address
(
    id            int auto_increment,
    province      varchar(15) not null,
    city          varchar(15) not null,
    area          varchar(15) not null,
    detail        varchar(50) not null,
    signer_name   varchar(8)  not null,
    signer_mobile char(11)    not null,
    user_id       int         not null,
    create_time   datetime    not null,
    update_time   datetime    null,
    is_delete     tinyint(1) default 0,
    primary key (id)
);