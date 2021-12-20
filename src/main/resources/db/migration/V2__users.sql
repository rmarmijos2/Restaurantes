create table if not exists users (
    id serial,
    username varchar(45) not null,
    password varchar(45) not null,
    primary key (id),
    UNIQUE (username)
    );