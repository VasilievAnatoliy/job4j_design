insert into role(role) values ('a');
insert into role(role) values ('b');
insert into role(role) values ('c');
insert into role(role) values ('d');

insert into rules(rule) values ('x');
insert into rules(rule) values ('y');
insert into rules(rule) values ('z');

insert into role_rules(role_id, rules_id) values (1, 1);
insert into role_rules(role_id, rules_id) values (2, 2);
insert into role_rules(role_id, rules_id) values (3, 3);
insert into role_rules(role_id, rules_id) values (1, 3);

insert into users(name, role_id) values ('Ivan', 1);
insert into users(name, role_id) values ('Petr', 2);
insert into users(name, role_id) values ('Vlad', 3);
insert into users(name, role_id) values ('Igor', 3);


insert into category(name) values ('first');
insert into category(name) values ('second');

insert into state(name) values ('m');
insert into state(name) values ('n');

insert into item(item, users_id, category_id,state_id) values (1, 1, 1, 2);
insert into item(item, users_id, category_id,state_id) values (20, 1, 1, 1);

insert into comments(name, item_id) values ('comment1', 1);
insert into comments(name, item_id) values ('comment2', 2);

insert into attachs(name, item_id) values ('access.log', 1);
insert into attachs(name, item_id) values ('access.log', 2);

