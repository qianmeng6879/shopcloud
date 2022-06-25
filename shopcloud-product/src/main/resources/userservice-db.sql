drop database if exists shopcloud_productservice;

create database shopcloud_productservice character set utf8;
use shopcloud_productservice;


create table category
(
    id        int auto_increment,
    name      varchar(20) unique not null,
    is_hot    tinyint(1) default 0,
    parent_id int        default 0,
    primary key (id)
);

insert into category
values (1, 'Apple', 0, 0);
insert into category
values (2, 'Mac', 0, 1);
insert into category
values (3, 'MacBook Air', 0, 2);
insert into category
values (4, 'MacBook Pro', 0, 2);
insert into category
values (5, 'iMac', 0, 2);
insert into category
values (6, 'iPad', 0, 1);
insert into category
values (7, 'iPhone', 0, 1);
insert into category
values (8, 'Samsung', 0, 0);
insert into category
values (9, 'Mobile', 0, 8);
insert into category
values (10, 'Computing', 0, 8);
insert into category
values (11, 'Billie', 0, 1);
insert into category
values (12, 'Sadye', 0, 1);



create table product
(
    id           int auto_increment,
    title        varchar(30)    not null,
    description  text           null,
    to_price     decimal(10, 2) null,
    cost_price   decimal(10, 2) null,
    stock        int            not null,
    code         char(30)       not null,
    hot          tinyint(1)              default 0,
    sales_volume int            not null default 0,
    main_picture varchar(128)   not null,
    unit         varchar(5)     not null,
    category_id  int            null,
    create_time  datetime       not null,
    update_time  datetime       null,
    is_delete    tinyint(1)              default 0,
    primary key (id)
);

insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (1, '13-inch MacBook Air', 'Apple M1 Chip with 8-Core CPU and 7-Core GPU', 960.78, 499, 999,
        'P00165189594533500001', 0, 'product/2e5e9cff-0e28-4d4e-8799-399b801d7b74.jpeg',
        '2022-06-04 16:35:48.526604', '2022-06-04 16:40:11.364162', 2, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (2, '13-inch MacBook Pro', 'Apple M1 Chip with 8‑Core CPU and 8‑Core GPU', 1299, 599, 100,
        'P00165189594579830001', 0, 'product/194336f1-3258-4d01-955e-f7d18f12759b.jpeg',
        '2022-06-04 16:40:01.563466', '2022-06-04 16:40:14.608609', 2, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (3, '14-inch MacBook Pro', '8-Core CPU 14-Core GPU 16GB Unified Memory', 1999, 999, 100, 'P00165189594552790001',
        0, 'product/cc707de7-83c1-4d69-b809-ebfe26c84c3c.jpeg', '2022-06-04 16:41:13.578047',
        '2022-06-04 16:41:13.578047', 2, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (4, '16-inch MacBook Pro', '10-Core CPU 32-Core GPU 32GB Unified Memory', 3499, 1999, 100,
        'P00165189594571000001', 0, 'product/d6a5c710-26e5-4c89-8424-345d0148a34a.jpeg',
        '2022-06-04 16:42:05.244917', '2022-06-04 16:42:05.244917', 2, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (5, '24 iMac', 'Apple M1 Chip 8-Core CPU 8-Core GPU', 1499, 999, 99, 'P00165189594514200001', 0,
        'product/83d6e7bc-1194-49ef-8074-5981e944ed82.jpeg', '2022-06-04 16:42:50.482577',
        '2022-06-04 16:57:07.859171', 2, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (6, '11-inch iPad Pro', 'Supercharged by the Apple M1 chip', 799, 499, 100, 'P00165189594542850001', 0,
        'product/84a7faa2-5f2a-4afe-b7f5-6003b1eb8628.png', '2022-06-04 16:43:43.367863',
        '2022-06-04 16:43:43.367863', 6, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (7, '12.9-inch iPad Pro', 'Supercharged by the Apple M1 chip', 999, 599, 100, 'P00165189594533190001', 0,
        'product/ef648a1a-6450-48a3-a1c7-14f884b38372.jpeg', '2022-06-04 16:44:25.629215',
        '2022-06-04 16:45:34.269534', 6, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (8, 'iPad Air', 'Light. Bright. Full of might', 599, 299, 100, 'P00165189594542230001', 0,
        'product/0ea6cc13-4f30-48f7-9d4d-e86c45a83a12.jpeg', '2022-06-04 16:45:13.167267',
        '2022-06-04 16:45:13.167267', 6, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (9, 'iPad',
        'Powerful. Easy to use. Versatile. The new iPad is designed for all the things you love to do. Work, play, create, learn, stay connected, and more. All at an incredible value',
        329, 199, 10, 'P00165189594581410001', 0, 'product/62c25e1a-f5b8-4b46-8e66-bd03d6191645.jpeg',
        '2022-06-04 16:46:29.920705', '2022-06-04 16:46:29.920705', 6, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (10, 'iPad mini', 'Mega power. Mini sized', 499, 299, 100, 'P00165189594585390001', 0,
        'product/2a11ea79-6d96-4d98-95b3-b9a0bc8496d5.jpeg', '2022-06-04 16:47:33.319930',
        '2022-06-04 16:53:23.805425', 6, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (11, 'iPhone 13 Pro', 'The ultimate iPhone', 999, 549, 100, 'P00165189594523030001', 0,
        'product/2030b1e4-9b52-4bea-bdcc-f2a2f1e219d2.jpeg', '2022-06-04 16:49:28.998489',
        '2022-06-04 16:49:28.998489', 7, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (12, 'iPhone 13 Pro Max', 'The ultimate iPhone', 1099, 599, 100, 'P00165189594585500001', 0,
        'product/13a8c619-301f-4069-9f0c-69302f972576.jpeg', '2022-06-04 16:50:06.695538',
        '2022-06-04 16:50:06.695538', 7, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (13, 'iPhone 13', 'A total powerhouse', 699, 399, 100, 'P00165189594528200001', 0,
        'product/05411e7d-aacf-4fa9-be86-3d22a88ff2e2.jpeg', '2022-06-04 16:51:22.309942',
        '2022-06-04 16:51:22.309942', 7, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (14, 'iPhone SE', 'Serious power. Serious value', 429, 299, 100, 'P00165189594585060001', 0,
        'product/536c72ec-aea1-4647-8eff-73c5a73fdeaa.jpeg', '2022-06-04 16:52:05.393959',
        '2022-06-04 16:52:05.393959', 7, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (15, 'Galaxy S22 Ultra', 'Galaxy', 199, 149, 100, 'P00165189594547280001', 0,
        'product/49b9ce9a-7d23-449a-8eb3-a7171b944950.jpeg', '2022-06-04 16:52:43.483361',
        '2022-06-04 16:52:43.483361', 8, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (16, 'Galaxy S22', 'Galaxy', 99, 69, 100, 'P00165189594595770001', 0,
        'product/3e78b02e-bf68-458c-983f-0898d582480a.jpeg', '2022-06-04 16:53:12.064248',
        '2022-06-04 16:53:12.064248', 8, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (17, 'Galaxy Tab S8', 'Wi-Fi | 8GB (RAM) + 128GB | Pink Gold', 499, 299, 100, 'P00165189594522220001', 0,
        'product/7149f2f7-33f3-4419-897f-2c6135b0d545.jpeg', '2022-06-04 16:54:01.374658',
        '2022-06-04 16:54:01.374658', 8, 'pcs');
insert into product (id, title, description, to_price, cost_price, stock, code, hot, main_picture, create_time,
                     update_time, category_id, unit)
values (18, 'Galaxy Book2 Pro', 'Intel® Core™ i7 | 13.3\" | 8GB + 512GB | Silver', 1249, 899, 100,
        'P00165189594552590001', 0, 'product/95466f8d-dfb5-4ae6-8446-bd1cce64b7da.jpeg',
        '2022-06-04 16:54:36.879852', '2022-06-04 16:54:36.879852', 8, 'pcs');


create table banner
(
    id           int auto_increment,
    image_url    varchar(128) not null,
    introduction varchar(20)  null,
    primary key (id)
);

insert into banner
values (1, 'banner/default.jpg', '默认');
insert into banner
values (2, 'banner/2.jpg', '默认');
insert into banner
values (3, 'banner/3.jpg', '默认');