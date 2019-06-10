create database dad encoding = 'UTF8';
create table body
(
    id_body   serial primary key,
    name_body character varying(2000) NOT NULL
);
create table engine
(
    id_engine   serial primary key,
    name_engine character varying(2000) NOT NULL
);
create table transmit
(
    id_transmit   serial primary key,
    name_transmit character varying(2000) NOT NULL
);
create table auto
(
    id_auto     serial primary key,
    brand_auto  character varying(2000)                   NOT NULL,
    id_body     integer references body (id_body)         NOT NULL,
    id_engine   integer references engine (id_engine)     NOT NULL,
    id_transmit integer references transmit (id_transmit) NOT NULL
);
create table detail
(
    id_detail   serial primary key,
    id_auto     integer references auto (id_auto),
    id_body     integer references body (id_body),
    id_engine   integer references engine (id_engine),
    id_transmit integer references transmit (id_transmit),
    name_detail character varying(2000) NOT NULL
);
insert into body (name_body)
values ('sedan'),
       ('SUV'),
       ('coupe');
insert into engine (name_engine)
values ('patrol'),
       ('diesel'),
       ('electra');
insert into transmit (name_transmit)
values ('manual'),
       ('automate'),
       ('robot');
insert into auto (brand_auto, id_body, id_engine, id_transmit)
values ('bmw', '2', '2', '2'),
       ('tesla', '1', '3', '2'),
       ('mercedes', '3', '1', '1');
insert into detail (id_auto, id_body, name_detail)
values ('1', '1', 'door'),
       ('1', '1', 'hood'),
       ('1', '1', 'window'),
       ('2', '1', 'door'),
       ('2', '1', 'hood'),
       ('3', '1', 'window');
insert into detail (id_auto, id_engine, name_detail)
values ('1', '2', 'candle'),
       ('1', '2', 'cylinder'),
       ('2', '2', 'cylinder'),
       ('3', '2', 'inductor');
insert into detail (id_auto, id_transmit, name_detail)
values ('1', '3', 'clutch'),
       ('1', '3', 'clutch switch'),
       ('2', '3', 'clutch switch'),
       ('3', '3', 'clutch ');
--old query
--1. Вывести список всех машин и все привязанные к ним детали.
EXPLAIN with tmp(brand_auto, name_detail) as
                 (select a.brand_auto,
                         d.name_detail
                  from detail d,
                       auto a
                           join detail ad using (id_auto)
                  where ad.id_detail = d.id_detail)
        select * from tmp ORDER BY brand_auto;
--new query
--1. Вывести список всех машин и все привязанные к ним детали.
EXPLAIN select
            a.brand_auto,
            d.name_detail
        from auto a
                 inner join detail ad on a.id_auto = ad.id_auto
                 inner join detail d on ad.id_detail = d.id_detail
        order by a.brand_auto;
--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
EXPLAIN select d.name_detail
        from detail d
                 natural join auto a
        where name_detail = (
            select d.name_detail
            from detail d
                     left join auto a on d.id_auto = a.id_auto
            where d.id_detail is null);
