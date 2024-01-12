-- noinspection SqlNoDataSourceInspectionForFile

-- noinspection SqlDialectInspectionForFile
delete from vaccine_side_effect;
delete from vaccine;
delete from side_effect;
delete from user_authority;
delete from user;
delete from authority;


insert into vaccine (id, research_name, manufacturer_name, numberOfShots, quantity, vaccine_type)
values (1, 'mRNA-1273', 'ModernaTX', 2, 100, 'RNA');
insert into vaccine (id, research_name, manufacturer_name, numberOfShots, quantity, vaccine_type)
values (2, 'BNT162b2', 'Pfizer & BioNTech', 2, 1000, 'RNA');
insert into vaccine (id, research_name, manufacturer_name, numberOfShots, quantity, vaccine_type)
values (3, 'JNJ-78436735', 'Johnson & Johnson', 1, 10, 'VIRALNI_VEKTOR');


insert into side_effect (id, kratki_opis, dugi_opis, ucestalost)
values (1, 'Temperatura', 'Temperatura koja prelazi 38 stupnjeva celzijevih!', 5);
insert into side_effect (id, kratki_opis, dugi_opis, ucestalost)
values (2, 'Osip', 'Osip na mjestu oko uboda ili po ruci u koju je primljeno cjepivo!', 3);
insert into side_effect (id, kratki_opis, dugi_opis, ucestalost)
values (3, 'Mučnina', 'Slabost u probavnom traktu nastupa dan nakon cijepljenja!', 2);

insert into vaccine_side_effect (id, vaccine_id, side_effect_id)
values (1, 1, 1);
insert into vaccine_side_effect (id, vaccine_id, side_effect_id)
values (2, 1, 2);
insert into vaccine_side_effect (id, vaccine_id, side_effect_id)
values (3, 2, 3);
insert into vaccine_side_effect (id, vaccine_id, side_effect_id)
values (4, 3, 2);

insert into user(id, username, password, first_name, last_name) values (1, 'admin', '$2y$12$r88zMLI9WK8BqYXeIOQqxevSYABYAQUam8C6mHaepUIUvMFGRu1oC', 'admin', 'admin');
insert into user(id, username, password, first_name, last_name) values(2, 'user', '$2y$12$UVPuW1YuEKc4lpwtZS2KSuAz3hTkhPlYCXRLUWwwC/kWMneZiwpNG', 'user', 'user');
insert into user(id, username, password, first_name, last_name) values(3, 'creator', '$2y$12$PEFnh4V4.oXUROFDl7ldNutFqYGiNxPmTQQ/o/LtDq2tiHd8pMwj.', 'creator', 'creator');
insert into user(id, username, password, first_name, last_name) values(4, 'test', 'test', 'test', 'test');
insert into authority(id, name) values(1, 'ROLE_ADMIN');
insert into authority(id, name) values(2, 'ROLE_USER');
insert into authority(id, name) values(3, 'ROLE_CREATOR');
insert into user_authority(user_id, authority_id) values(1, 1);
insert into user_authority(user_id, authority_id)values(2, 2);
insert into user_authority(user_id, authority_id)values(3, 3);
insert into user_authority(user_id, authority_id)values(4, 1);
