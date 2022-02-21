create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('phone', 20000), ('phone', 2500),
	('tablet', 3000), ('tv', 35000);
insert into people(name) values ('Аня'), ('Ваня'), ('Боря');
insert into devices_people(people_id, device_id) values (1, 1), (1, 3), (1, 4);
insert into devices_people(people_id, device_id) values (2, 2), (2, 4);
insert into devices_people(people_id, device_id) values (3, 1), (3, 4);

select avg(price)
from devices;

select people.name as Имя, avg(devices.price) as средняя_цена_устройств
from devices_people as dp
join devices on dp.device_id = devices.id
join people on dp.people_id = people.id
group by people.name;

select people.name as Имя, avg(devices.price) as средняя_цена_устройств
from devices_people as dp
join devices on dp.device_id = devices.id
join people on dp.people_id = people.id
group by people.name
having avg(devices.price) > 5000
