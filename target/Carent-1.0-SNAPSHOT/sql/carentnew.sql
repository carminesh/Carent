drop database if exists vehicle_rental;
create database vehicle_rental;
use vehicle_rental;

drop table if exists utente;
create table utente (
	userCode int not null auto_increment,
	email varchar(30) not null,
    password varchar(256) not null,
    role varchar(30) not null default "userrole",
    name varchar(30) not null,
    surname varchar(30) not null,
    phone char(10) not null,
    primary key (userCode),
    unique (email)
) engine = InnoDB;

drop table if exists veicolo;
create table veicolo (
	targa char(7) primary key,
    marca varchar(30) not null,
    modello varchar(30) not null,
    alimentazione varchar(30) not null default "Benzina",
    chilometraggio int not null default 0,
    potenza int not null,
    anno_imm year not null,
    foto mediumblob default null
) engine = InnoDB;

drop table if exists carrello;
create table carrello (
	codiceItem int not null auto_increment,
	userCode int not null,
    targa char(7) not null,
    daData date not null,
    aData date not null,
    primary key (codiceItem),
    constraint chiave_esterna_to_utente_from_carrello foreign key (userCode) references utente(userCode) on delete cascade on update cascade,
    constraint chiave_esterna_to_veicolo_from_carrello foreign key (targa) references veicolo(targa) on delete restrict on update cascade
) engine = InnoDB;

select * from veicolo;
select * from utente;
select * from carrello;

insert into utente(email,password,role, name, surname,phone) values 
("puddino1337@gmail.com","d572cbaf9215ad1ca9466224210d127761f11078194f279536a747e5f28b148c","userrole","Dario", "Trinchese","3937323328"),
("marcotrinchese@gmail.com","d572cbaf9215ad1ca9466224210d127761f11078194f279536a747e5f28b148c","userrole","Marco", "Trinchese","3937324515"),
("mrisi@uynisa.it","cebb96c9d2ce4a33fc21622710510ed6c55ce31889ccc5058a073faffea999d5","adminrole","Michele","Risi","0615448796"),
("admin@carent.it","8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918","adminrole","Dario","Trollo","4566541230");

INSERT INTO veicolo(targa,marca,modello,alimentazione,chilometraggio,potenza,anno_imm) VALUES
('DW461EM','Opel','Astra','GPL',160000,67,2009),
('BX797NA','Mercedes','A170L','DIESEL',210000,77,2001),
('BX563EM','FIAT','Panda','BENZINA',141000,44,2002),
('EG795NO','FIAT','Panda','BENZINA',66000,51,2011),
('FW765AZ','Opel','Mokka','DIESEL',51000,77,2018),
('GD544BM','FIAT','500L','BENZINA',12000,67,2020),
('CX700NA','Opel','Zafira','DIESEL',180000,77,2003),
('EZ133DD','Peugeot','107','BENZINA',100000,44,2013),
('GA913CE','Opel','Corsa','BENZINA',44000,75,2019),
('GB859ZR','Mercedes','AMG GT','DIESEL',5000,102,2020),
('GC741KL','Volkswagen','E-UP','ELETTRICO',15000,51,2020),
('FM456KE','Hyundai','Tucson','BENZINA',68000,88,2018),
('FB563TW','Citroen','C3','BENZINA',59000,51,2018),
('EM099LI','Renault','Clio','DIESEL',41000,90,2014),
('DV218ZH','FIAT','Grande Punto','METANO',150000,51,2009),
('EZ989AO','Alfa Romeo','MiTo','DIESEL',12000,70,2010);

SET SQL_SAFE_UPDATES=0;
delete from carrello;

drop table if exists noleggio;
create table noleggio (
	rentCode int not null auto_increment,
	userCode int not null,
    targa char(7) not null,
    daData date not null,
    aData date not null,
    prezzo float,
    primary key (rentCode),
    constraint chiave_esterna_to_utente_from_noleggio foreign key (userCode) references utente(userCode) on update cascade on delete restrict,
    constraint chiave_esterna_to_veicolo_from_noleggio foreign key (targa) references veicolo(targa) on update cascade on delete restrict
) engine=InnoDB;

insert into noleggio(prezzo,daData,aData,userCode,targa) values
(400,'2020-10-13','2021-02-23',1,'DW461EM'),
(510,'2020-10-13','2021-01-15',2,'GD544BM'),
(600,'2020-10-13','2021-03-10',3,'GC741KL'),
(700,'2020-10-13','2021-01-18',4,'FW765AZ'),
(122,'2020-10-13','2020-12-31',4,'BX563EM'),
(110,'2020-10-13','2020-11-13',3,'GA913CE'),
(320,'2020-10-13','2020-11-13',2,'EG795NO'),
(980,'2020-10-13','2021-01-05',1,'FM456KE'),
(650,'2020-10-13','2021-03-25',2,'CX700NA'),
(700,'2020-10-14','2021-08-09',2,'EZ989AO'),
(1000,'2020-10-14','2021-10-14',1,'EM099LI');