create table departments(
	id serial primary key,
	name varchar(255)
);

create table emploers(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments(name) values('department_1'), ('department_2'), ('department_3');
insert into emploers(name, department_id)
	values('Ivan', 1), ('Petr', 1), ('Olga', 2), ('Anna', 2), ('Vlad', 3), ('Alex', null);

/* запросы с left, right, full, cross соединениями */
select * from departments d left join emploers e on d.id = e.department_id;
select * from departments d right join emploers e on d.id = e.department_id;
select * from departments d full join emploers e on d.id = e.department_id;
select * from departments cross join emploers;

/* Используя left join найти департаменты, у которых нет работников */
insert into departments(name) values('department_4');
select * from departments d left join emploers e on d.id = e.department_id
where e.department_id is null;

/* Используя left и right join написать запросы, которые давали бы одинаковый
   результат (порядок вывода колонок в эти запросах также должен быть идентичный) */
select * from departments d left join emploers e on d.id = e.department_id
	where e.department_id is not null;
select * from departments d right join emploers e on d.id = e.department_id
	where d.id is not null;



/* Создать таблицу teens с атрибутами name, gender и заполнить ее */
create table teens(
	name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values('Ivan', 'm'), ('Olga', 'w'), ('Anna', 'w'),
	('Petr', 'm'), ('Max', 'm'), ('Vlad', 'm'), ('Nadya', 'w');

/* Используя cross join составить все возможные разнополые пары */
select x1.name, x2.name from teens x1 cross join teens x2
    where x1.gender <> x2.gender;