CREATE TABLE IF NOT EXISTS vacancy
(
    id           serial primary key,
    date_vacancy character varying(20)   NOT NULL,
    name_vacancy character varying(2000) NOT NULL,
    desc_vacancy text,
    link_vacancy character varying(2000) NOT NULL
);
