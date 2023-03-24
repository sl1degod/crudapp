# crudapp
scripts for bd
create table Cities (
	id int primary key not null generated always as identity (start with 1 increment by 1),
	name varchar(50) not null
);

create table LevelEducation (
	id int primary key not null generated always as identity (start with 1 increment by 1),
	name varchar(50) not null
);

create table GroupType (
	id int primary key not null generated always as identity (start with 1 increment by 1),
	name varchar(50) not null
);

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

create table FormEducation (
	id int primary key not null generated always as identity (start with 1 increment by 1),
	name varchar(50) not null
);

create table Information (
	id int primary key not null generated always as identity (start with 1 increment by 1),
	name varchar(50) not null,
	termOfStudy varchar(50) not null,
	plan int not null,
	formEdID int not null,
	FOREIGN KEY (formEdID) REFERENCES formeducation(id)
);

create table Specialization (
	id int primary key not null generated always as identity (start with 1 increment by 1),
	name varchar(50) not null,
	gtypeID int not null,
	infID int not null,
	price int not null,
	FOREIGN KEY (infID) REFERENCES Information(id),
	FOREIGN KEY (gtypeID) REFERENCES GroupType(id)
);

create table Form (
	id int primary key not null generated always as identity (start with 1 increment by 1),
	abitID int not null,
	formEduc varchar(50) not null,
	spec varchar(200) not null,
	status text default 'На рассмотрении',
	FOREIGN KEY (abitID) REFERENCES Abiturient(id)
);

create table Results (
	id int primary key not null generated always as identity (start with 1 increment by 1),
	formID int not null,
	status text not null,
	FOREIGN KEY (formID) REFERENCES Form(id)
);






insert into employees (surname, name, patronymic)
values
('Иванов', 'Иван', 'Иванович')
