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
