CREATE DATABASE IF NOT EXISTS petsitter;


drop table	IF EXISTS reservation;
drop table	IF EXISTS confirm_new_account;
drop table	IF EXISTS users_roles;
drop table	IF EXISTS users;
drop table	IF EXISTS roles;
drop table	IF EXISTS address;


create table address (
id 			int		 		NOT NULL AUTO_INCREMENT,
country 	varchar(255)	NOT NULL,
state		varchar(255)	NOT NULL,
city		varchar(255)	NOT NULL,
street		varchar(255),
house_no	varchar(20)		NOT NULL,
flat_no		varchar(20),
PRIMARY KEY(id)
);

create table roles(
role_id 	int 			NOT NULL AUTO_INCREMENT,
name		varchar(255)	NOT NULL,
PRIMARY KEY(role_id)
);

create table users (
user_id 	int				NOT NULL AUTO_INCREMENT,
name		varchar(255)	NOT NULL,
lastname 	varchar(255)	NOT NULL,
login	 	varchar(255)	NOT NULL,
password 	varchar(255)	NOT NULL,
email		varchar(255)	NOT NULL,
enabled 	bit				NOT NULL,
address_id	int				NOT NULL,
PRIMARY KEY(user_id)
);
ALTER TABLE users
ADD FOREIGN KEY (address_id) REFERENCES address(id);


create table users_roles(
id			int				NOT NULL AUTO_INCREMENT,
user_id 	int				NOT NULL,
role_id		int				NOT NULL,
PRIMARY KEY(id)
);
ALTER TABLE users_roles
ADD FOREIGN KEY (role_id) REFERENCES roles(role_id);
ALTER TABLE users_roles
ADD FOREIGN KEY (user_id) REFERENCES users(user_id);


create table confirm_new_account(
id			int				NOT NULL AUTO_INCREMENT,
code		varchar(255)	NOT NULL,
u_email		varchar(255)	NOT NULL,
PRIMARY KEY(id)
);


create table reservation(
id				int				NOT NULL AUTO_INCREMENT,
start_date		datetime		NOT NULL,
end_date		datetime		NOT NULL,
owner_id		int,
petsitter_id	int,
PRIMARY KEY(id)
);
ALTER TABLE reservation
ADD FOREIGN KEY (owner_id) REFERENCES users(user_id);
ALTER TABLE reservation
ADD FOREIGN KEY (petsitter_id) REFERENCES users(user_id);


insert into address(country, state, city, street, house_no, flat_no)
values ('Polska', 'ma³opolskie', 'Tarnów', '3 maja', '3', '4'),
('Polska', 'mazowieckie', 'Warszawa', 'Szkolna', '2', '3'),
('Polska', 'wielkopolskie', 'Poznañ', 'Wa³owa', '12', '1'),
('Polska', 'mazowieckie', 'Warszawa', 'Centralna', '2', '13'),
('Polska', 'wielkopolskie', 'Poznañ', 'Wielkopolska', '52', '3'),
('Polska', 'ma³opolskie', 'Kraków', 'Stella', '4', '3'),
('Polska', 'ma³opolskie', 'Tarnów', 'Wa³owa', '21', '3');


insert into roles(name)
values ('ADMIN'), ('PETSITTER'), ('OWNER');

insert into users(name, lastname, login, password, email, enabled, address_id)
values ('Arkadiusz', 'Kowalski', 'arek99', '$2a$12$q0tqoIJ.QvJuxxFCY9buOuu0Rh/XHdHjEpbND//VJTbbao3Hq1qHm','arek9905@gmail.com', 1, 1),
('Jan', 'Kowalski', 'jan99', '$2a$12$KRYqwuwEyauWkHCCXmXE/ur6787N4cH.nHeTweiguKj.zvIGiD3FG','jan99@gmail.com', 1, 2),
('Adam', 'Kowalski', 'adam99', '$2a$10$GIWiKel4W4iysQ3TX1j7EOV9U0M2ooJHmt8kn9TGQstkfnIFYYtAq','adam9906@gmail.com', 1, 3),
('Mariusz', 'Wiœniewski', 'mar99', '$2a$10$NTK2Xb1bDQy70J.sejCSm.U6ZS3EShM7JP.VXYuUDV23au3BiphQO','mar99@gmail.com', 1, 4),
('Marek', 'Kania', 'marek99', '$2a$10$g8g3H8YiAmFsZMWpKmI9ceXPJxIZRno9zGXMv0m5m.z6jBeDAU4bW','marek99@gmail.com', 1, 5),
('Anna', 'Smolarek', 'anna99', '$2a$10$JEK8DbG0eZWCTtdkpCXNPO.T5oGiranDVf83caQtLGCIwtDTwGGAO','anna99@gmail.com', 1, 6),
('Roman', 'Smolarek', 'roman99', '$2a$10$LI1iIjkxDg.5vXz1wNT0d.h2zAJ2N1EFEtP7Of/q5eHj9042gZSqq','roman99@gmail.com', 1, 7);

insert into users_roles(user_id, role_id)
values (1,1), (2,2), (3,3), (4,2), (5,2), (6,2), (7,2);

insert into confirm_new_account(code, u_email)
values (611833, 'ann99@gmail.com');

insert into reservation(start_date, end_date, owner_id, petsitter_id)
values ('2023-03-01 17:00:00.000000', '2023-03-02 17:00:00.000000', 3, 2),
('2023-03-05 10:00:00.000000', '2023-03-05 16:00:00.000000', 3, 5);