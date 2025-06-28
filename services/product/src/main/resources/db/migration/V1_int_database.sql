create table if not exists category
    (
        id integer not null primary key,
        name varchar(70) not null,
        description varchar(250) not null
);

create table if not exists product
(
    id integer not null primary key,
    name varchar(70) not null,
    description varchar(250) not null,
    available_quantity double precision not null,
    price numeric(38, 2) not null,
    id_category integer constraint fk1 references category
);

--  garantir que diferentes nós não gerem IDs conflitantes.
create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;