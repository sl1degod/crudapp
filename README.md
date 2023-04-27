# crudapp

create table Abiturient (
	id int primary key not null generated always as identity (start with 1 increment by 1),
	surname varchar(50) not null,
	name varchar(50) not null,
	patronymic varchar(50) not null,
	dateBirthday date not null,
	phone text not null,
	city varchar(50) not null,
	image text,
	avgScore real not null,
	login text not null,
	password text not null
);

create table Employees (
	id int primary key not null generated always as identity (start with 1 increment by 1),
	surname varchar(50) not null,
	name varchar(50) not null,
	patronymic varchar(50) not null,
	role text default 'Зав. отделения',
	login text default 'admin',
	password text default 'admin'
);


create table Form (
	id int primary key not null generated always as identity (start with 1 increment by 1),
	abitID int not null,
	formEduc varchar(100) not null,
	spec varchar(200) not null,
	status text default 'На рассмотрении',
	FOREIGN KEY (abitID) REFERENCES Abiturient(id)
);


insert into employees (surname, name, patronymic)
values
('Иванов', 'Иван', 'Иванович');



insert into abiturient (surname, name, patronymic, datebirthday, phone, image, city, avgscore, login, password)
values
('Иванов', 'Иван', 'Иванович', '23-06-2000', '89534123848', 'D:\layers.png','Альметьевск', 4, 'qwe', 'sl1degOd'),
('asd', 'asd', 'asd', '23-06-2000', '89534123848', 'D:\layers.png','Казань', 4, 'zxc', 'sl1degOd'),
('zxc', 'zxc', 'zxc', '23-06-2000', '89534123848', 'D:\layers.png','Уфа', 4, 'aaa', 'sl1degOd');



create or replace function check_abit_exists() returns trigger 
as 
$$
begin
	if exists (select * from form where spec = new.spec and abitid = new.abitid) then 
	raise exception 'Вы уже создавали анкету с такой специальностю';
	end if;
	return new;
end;
$$
language plpgsql;

create or replace trigger check_abit_exists_trigger
before insert on form 
for each row 
execute function check_abit_exists();

