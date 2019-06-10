create database poy encoding ='utf8';
create table toy
(
    id_toy   serial primary key,
    name_toy character varying(2000) NOT NULL
);
create table girl
(
    id_girl   serial primary key,
    name_girl character varying(2000) NOT NULL
);
create table tmp
(
    id_tmp  serial primary key,
    id_toy  integer references toy (id_toy)   NOT NULL,
    id_girl integer references girl (id_girl) NOT NULL
);
insert into toy (name_toy)
values ('horse'),
       ('cat'),
       ('dog'),
       ('boll');
insert into girl(name_girl)
values ('Olga'),
       ('Katya'),
       ('Nina');
insert into tmp(id_girl, id_toy)
values ('1', '1'),
       ('2', '1'),
       ('2', '2'),
       ('3', '2'),
       ('3', '3');
-- many-to-many
-- одна игрушка может быть у разных девочек
-- у одной девочки могут быть разные или одинаковые игрушки
-- получить игрушки которых нет у Ольги
select *
from (girl g natural join tmp p)
         inner join toy t on p.id_toy <> t.id_toy
where g.name_girl ~ 'Olga';
-- получить игрушки Ольги
select *
from (girl g natural join tmp p)
         inner join toy t on p.id_toy = t.id_toy
where g.name_girl ~ 'Olga';

