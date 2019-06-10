create database  toy encoding ='utf8';
create table toy(
    id_toy serial primary key,
    name_toy character varying (2000) NOT NULL
);
create table girl (
    id_girl  serial primary key,
    name_girl character varying (2000) NOT NULL ,
    id_toy integer references toy( id_toy) NOT NULL
);
insert into toy (name_toy) values ('horse'),('cat'),('dog'),('boll');
insert into girl(name_girl, id_toy) values ('Olga','1'),('Katya','2'),('Nina','3');
--  the query demand toys which girls don't have.
select * from toy t left join girl g using (id_toy) where g.id_girl isnull;
--  the query demand toys which girls don't have.
select * from toy t left join girl g using (id_toy) where g.id_toy isnull;
--  the query demand toys which each girl has
select  * from girl g inner join toy t on g.id_toy = t.id_toy;
--  the query demand toys which each girl has not
select  * from girl g inner join toy t on g.id_toy <> t.id_toy;
