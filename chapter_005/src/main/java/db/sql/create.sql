CREATE DATABASE library
    ENCODING = 'UTF8'
;
CREATE TABLE rules
(
    id_rule serial primary key,
    name_rule character varying(2000) NOT NULL
);
create table roles
(
    id_role   serial primary key,
    name_role character varying(2000) NOT NULL
);
create table roles_rules
(
    id_roles_rules serial primary key,
    id_roles_fk integer references roles (id_role) NOT NULL,
    id_rules_fk integer references rules (id_rule) NOT NULL
);
create table users
(
    id_user   serial primary key,
    id_roles_rules_fk integer references roles_rules (id_roles_rules) NOT NULL,
    name_user character varying(2000) NOT NULL
);
insert into roles (name_role) values ('RoleA');
insert into roles (name_role) values ('RoleB');
insert into roles (name_role) values ('RoleC');
insert into rules (name_rule) values ('1');
insert into rules (name_rule) values ('0');
insert into rules (name_rule) values ('2');
insert into rules (name_rule) values ('3');
insert into rules (name_rule) values ('5');
insert into rules (name_rule) values ('6');
insert into rules (name_rule) values ('7');
insert into roles_rules (id_roles_fk, id_rules_fk) values ('1','1');
insert into roles_rules (id_roles_fk, id_rules_fk) values ('1','2');
insert into roles_rules (id_roles_fk, id_rules_fk) values ('2','3');
insert into roles_rules (id_roles_fk, id_rules_fk) values ('2','4');
insert into roles_rules (id_roles_fk, id_rules_fk) values ('3','5');
insert into roles_rules (id_roles_fk, id_rules_fk) values ('3','6');
insert into roles_rules (id_roles_fk, id_rules_fk) values ('3','1');
insert into users (id_roles_rules_fk, name_user) values (1,  'Java');
insert into users (id_roles_rules_fk, name_user) values (2,  'Class');
insert into users (id_roles_rules_fk, name_user) values (3,  'Jar');
create table itemCategory
(
    id_category   serial primary key,
    name_category character varying(2000) NOT NULL
);
create table itemState
(
    id_state   serial primary key,
    name_state character varying(2000) NOT NULL
);
create table itemComment
(
    id_comment  serial primary key,
    date_change timestamp NOT NULL,
    comment     text      NOT NULL
);
create table itemAttach
(
    id_attach   serial primary key,
    name_attach character varying(2000) NOT NULL
);
insert into itemCategory (name_category) values ('warn');
insert into itemCategory (name_category) values ('error');
insert into itemCategory (name_category) values ('fatal');
insert into itemState (name_state) values ('start');
insert into itemState (name_state) values ('pass');
insert into itemState (name_state) values ('end');
insert into itemAttach (name_attach) values ('red');
insert into itemAttach (name_attach) values ('yellow');
insert into itemAttach (name_attach) values ('green');
insert into itemComment (comment, date_change) values ('be or not to be?', '2019-05-28 14:05:06'::timestamp);
insert into itemComment (comment, date_change) values ('Оставь надежду, всяк сюда входящий', '2019-05-28 14:05:06'::timestamp);
insert into itemComment (comment, date_change) values ('Abandon all hope, ye who enter here', '2019-05-28 14:05:06'::timestamp);
create table item
(
    id_item        serial primary key,
    name_item      character varying(2000)                       NOT NULL,
    id_category_fk integer references itemCategory (id_category) NOT NULL,
    id_state_fk    integer references itemState (id_state)       NOT NULL,
    id_comment_fk  integer references itemComment (id_comment)   NOT NULL,
    id_attach_fk   integer references itemAttach (id_attach)     NOT NULL,
    id_user_fk     integer references users (id_user)            NOT NULL

);
insert into item (name_item, id_category_fk, id_state_fk, id_comment_fk, id_attach_fk, id_user_fk)
values ('first', 1, 1, 1, 1, 2);
insert into item (name_item, id_category_fk, id_state_fk, id_comment_fk, id_attach_fk, id_user_fk)
values ('second', 2, 2, 2, 2, 2);
insert into item (name_item, id_category_fk, id_state_fk, id_comment_fk, id_attach_fk, id_user_fk)
values ('three', 3, 3, 3, 3, 3);
create table catalogItem
(
    id_catalog    serial primary key,
    id_item_fk    integer references item (id_item)  NOT NULL,
    id_user_fk    integer references users (id_user) NOT NULL
);
insert into catalogItem (id_user_fk, id_item_fk) values ('2', '2');
insert into catalogItem (id_user_fk, id_item_fk) values ('2', '3');


