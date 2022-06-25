drop database if exists shopcloud_orderservice;
create database shopcloud_orderservice character set utf8;

use shopcloud_orderservice;

create table t_order(
    id int auto_increment,
    code char(21) not null ,
    product_id int not null ,
    product_name varchar(20) not null ,
    product_count int not null ,
    unit_price decimal(10, 2) not null ,
    total_price decimal(10, 2) not null ,
    state tinyint(1) default 1,
    user_id int not null ,
    province      varchar(15) not null,
    city          varchar(15) not null,
    area          varchar(15) not null,
    detail        varchar(50) not null,
    signer_name   varchar(8)  not null,
    signer_mobile char(11)    not null,
    create_time datetime not null ,
    update_time datetime,
    is_delete tinyint(1) default 0,
    primary key (id)
);


