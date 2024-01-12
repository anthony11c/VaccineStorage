create table if not exists vaccine (
    id identity ,
    research_name varchar(30) not null unique,
    manufacturer_name varchar(20) not null,
    numberOfShots number not null,
    quantity number not null,
    vaccine_type varchar (15) not null
);

create table if not exists side_effect (
    id identity,
    kratki_opis varchar(30) not null,
    dugi_opis varchar(300) not null,
    ucestalost number not null
);

create table if not exists vaccine_side_Effect (
    id identity,
    vaccine_id bigint,
    side_effect_id bigint,
    constraint fk_vaccine foreign key (vaccine_id) references vaccine(id),
    constraint fk_sideEffect foreign key (side_effect_id) references side_effect(id)
);

create table if not exists user (
    id identity ,
    username varchar(50) not null,
    password varchar(250) not null,
    first_name varchar(250) not null,
    last_name varchar(250) not null
);

create table if not exists authority (
    id identity ,
    name varchar(100) not null
);

create table if not exists user_authority (
    user_id bigint not null,
    authority_id bigint not null,
    constraint fk_user foreign key (user_id) references user(id),
    constraint fk_authority foreign key (authority_id) references authority(id)
);
