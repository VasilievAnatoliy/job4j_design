create table animals(
	id serial primary key,
	name varchar(255),
	weight smallint,
	area text
);
insert into animals(name, weight, area)
	values('Гризли', '365', 'США');
select * from animals;
update animals set name = 'Гриззли', weight = '350';
select * from animals;
delete from animals;
select * from animals;