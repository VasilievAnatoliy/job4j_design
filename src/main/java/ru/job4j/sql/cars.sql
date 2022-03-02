create table body(
	id serial primary key,
	name varchar(255)
);

create table engine(
	id serial primary key,
	name varchar(255)
);

create table transmission(
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(name) values('купе'), ('седан'), ('пикап'), ('спорт');
insert into engine(name) values('1.6'), ('1.8'), ('2.0'), ('2.5 дизель');
insert into transmission(name) values('автомат'), ('робот'), ('5 ступенчатая'), ('6 ступенчатая');

insert into car(name, body_id, engine_id, transmission_id) values('ford', 1, 1, 1);
insert into car(name, body_id, engine_id, transmission_id) values('opel', 2, 2, 2);
insert into car(name, body_id, engine_id, transmission_id) values('niva', 3, 3, 3);

/* список всех машин и все привязанные к ним детали */
select car.name, b.name, e.name, t.name from car join body b on car.body_id = b.id
	join engine e on  car.body_id = e.id
	join transmission t on car.body_id = t.id;

/* детали которые не используются ни в одной машине (1 деталь - 1 запрос) */
select * from car full join body b on car.body_id = b.id where car.body_id is null;
select * from car full join engine e on  car.body_id = e.id where car.body_id is null;
select * from car full join transmission t on car.body_id = t.id where car.body_id is null;
