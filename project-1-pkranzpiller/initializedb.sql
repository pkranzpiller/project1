create table employee(id serial primary key, username text, firstname text, lastname text, password text);
create table manager(id serial primary key, username text, firstname text, lastname text, password text);
create table requests(id serial primary key, imageid text, employeeid int, approval text, managerid int, details text);

insert into employee(username, firstname, lastname, password)  values('username', 'firstname', 'lastname', 'password');
insert into employee(username, firstname, lastname, password) values('Knuckles', 'Knuckles', 'NotSonic', 'douknodeweh');
insert into employee(username, firstname, lastname, password) values('Jim', 'Jimmeh', 'Johnson', 'password');
insert into manager(username, firstname, lastname, password) values('Overlord', 'Your', 'Senpai', 'callmesenpai');

select * from employee;
select * from manager;
select * from requests;

--delete from requests where id=2;

drop table employee;
drop table manager;
drop table requests;


drop table stuff;