create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('МЯСО');
insert into product(name, type_id, expired_date, price) values('Сыр плавленный', 1, '2022-02-20', 250);
insert into product(name, type_id, expired_date, price) values('Сыр моцарелла', 1, '2022-03-28', 1000);
insert into product(name, type_id, expired_date, price) values('Сыр российский', 1, '2022-03-01', 800);
insert into product(name, type_id, expired_date, price) values('Молоко пискарёвское', 2, '2022-02-25', 105);
insert into product(name, type_id, expired_date, price) values('Кефир простоквашино', 2, '2022-02-18', 94);
insert into product(name, type_id, expired_date, price) values('Мороженное брикет', 2, '2022-04-15', 250);
insert into product(name, type_id, expired_date, price) values('свинина', 3, '2022-02-20', 370);
insert into product(name, type_id, expired_date, price) values('курица', 3, '2022-02-28', 400);

/* получение всех продуктов с типом "СЫР" */
select product.name as Сыр
from type join product
on product.type_id = type.id
where type.name = 'СЫР';

/* получения всех продуктов у кого в имени есть слово "Мороженое" */
select * from product where name like '%Мороженное%';

/* все продукты, срок годности которых уже истек */
select * from product
where expired_date < current_date;

/* самый дорогой продукт */
select *from product
where price = (select max(price) from product);

/* выводит для каждого типа количество продуктов к нему принадлежащих.
   В виде имя_типа, количество */
select type.name as имя_типа, count(*) as количество
from type join product on product.type_id = type.id
group by type.name;

/* получение всех продуктов с типом "СЫР" и "МОЛОКО" */
select p.name
from type join product p
on p.type_id = type.id
where type.name in ('СЫР', 'МОЛОКО');

/* выводит тип продуктов, где наиминований продуктов данного типа меньше 10 штук */
select type.name as имя_типа, count(*) as количество
from type join product on product.type_id = type.id
group by type.name
HAVING count(*) < 10;

/* Вывести все продукты и их тип */
select * from type
join product p on p.type_id = type.id;