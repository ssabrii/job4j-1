CREATE DATABASE product
    ENCODING = 'UTF8'
;
create table type_product (
                              id        serial primary key,
                              name_type character varying(2000) NOT NULL
);
create table product (
                         id_product   serial primary key,
                         name_product character varying(2000)              NOT NULL,
                         price        integer                              NOT NULL,
                         expired_date date                                 NOT NULL,
                         type_id_fk   integer references type_product (id) NOT NULL
);
insert into type_product (name_type) values ('oil');
insert into type_product (name_type) values ('milk');
insert into type_product (name_type) values ('sugar');
insert into type_product (name_type) values ('protein');
insert into product (name_product, price, expired_date, type_id_fk)
values ('CHEESE', 50, '05.22.2019', 1);
insert into product (name_product, price, expired_date, type_id_fk)
values ('ICE CREAM', 5, '05.23.2019', 2);
insert into product (name_product, price, expired_date, type_id_fk)
values ('ICE BREAM', 5, '05.23.2019', 2);
insert into product (name_product, price, expired_date, type_id_fk)
values ('MILK', 10, '06.25.2019', 2);
insert into product (name_product, price, expired_date, type_id_fk)
values ('MEAT', 100, '06.26.2019', 4);
insert into product (name_product, price, expired_date, type_id_fk)
values ('FISH', 200, '05.20.2019', 4);
insert into product (name_product, price, expired_date, type_id_fk)
values ('CANDY', 7, '06.20.2019', 3);
-- 0 // bonus
select * from product order by id_product;
select * from type_product order by id;
--1// query  all products with type "milk"
select * from product p inner join  type_product t on p.type_id_fk=t.id and name_type = 'milk';
--2// query all products that have word "ICE" in name product
select * from product where name_product like 'ICE%';
--3// query all products the expiration date of which ends next month.
select * from product where expired_date > now()::date and expired_date < now()::date + 30;
--4// query most expensive product.
select * from product where price = (select max(price) from product);
--5.0// Write query the number of all products of certain type.
--5.1// New column 'amount' added. The values are set,
--5.1// cause task example there is no 'amount' column.
alter table product add column amount integer not null default 0;
update product set amount = 5  where id_product = 1;
update product set amount = 10 where id_product = 2;
update product set amount = 8  where id_product = 3;
update product set amount = 12 where id_product = 4;
update product set amount = 14 where id_product = 5;
update product set amount = 15 where id_product = 6;
update product set amount = 20 where id_product = 7;
--5.0 // query the number of all products of certain type.
select  name_type, sum(amount) from product p, type_product t where p.type_id_fk = t.id group by name_type;
--6 // query all products with type 'milk' and 'protein'.
select name_type, id_product, name_product, price, amount, expired_date from product p inner join type_product t
ON p.type_id_fk = t.id and (t.name_type = 'milk' OR t.name_type = 'protein');
--7 // query types products which are less 10.
select name_type from type_product t inner join product p on p.type_id_fk = t.id and p.amount < 10;
SELECT t.name_type
FROM product AS p LEFT JOIN type_product AS t ON t.id = p.type_id_fk
GROUP BY t.name_type
HAVING COUNT(p.id_product) > 10;




