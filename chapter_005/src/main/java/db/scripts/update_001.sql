CREATE TABLE IF NOT EXISTS item
(
    id        serial primary key,
    id_item   character varying(2000) NOT NULL,
    name_item character varying(2000) NOT NULL,
    desc_item text
);
