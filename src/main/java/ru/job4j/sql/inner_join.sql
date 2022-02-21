create table author(
	id serial primary key,
	name_author varchar(255)
);

create table book(
	id serial primary key,
	title varchar(255),
	author_id int references author(id)
);

insert into author(name_author) values ('Булгаков М.А.');
insert into book(title, author_id) values ('Мастер и Маргарита', 1);
insert into book(title, author_id) values ('Белая гвардия', 1);

select * from author as a
join book as b
on a.id = b.author_id;

select b.title, a.name_author
from author as a
join book as b
on a.id = b.author_id;

select b.title as Название, a.name_author as Автор
from book as b
join author as a
on b.author_id = a.id;
