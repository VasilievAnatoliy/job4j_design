 create table cars(
     id serial primary key,
     name varchar(255)
 );

 create table people(
     id serial primary key,
     name varchar(255)
 );

 create table cars_people(
     id serial primary key,
     cars_id int references cars(id),
     people_id int references people(id)
 );

insert into cars(name) values('bmv');
insert into cars(name) values('bus');
insert into cars(name) values('audi');

insert into people(name) values ('Ivan');
insert into people(name) values ('Kirill');
insert into people(name) values ('Roman');

insert into cars_people(cars_id, people_id) values (1, 1);
insert into cars_people(cars_id, people_id) values (1, 3);
insert into cars_people(cars_id, people_id) values (2, 1);
insert into cars_people(cars_id, people_id) values (2, 2);
insert into cars_people(cars_id, people_id) values (2, 3);
insert into cars_people(cars_id, people_id) values (3, 3);

select * from cars_people;