create table vin(
	id serial primary key
	vin int
);

create table car(
	id serial primary key
	neme varchar(255)
	vin_id int references vin(id) unique
);