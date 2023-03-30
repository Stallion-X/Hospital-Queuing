create table doctor(
dnum varchar(20) primary key,
dname varchar(50) not null,
dsex varchar(10) check(dsex in('ÄĞ','Å®')),
dage int check(dage >= 30 and dage <= 100),
dtel char(11) not null,
dtitle varchar(20),
dpost varchar(50),
dedu varchar(50),
ddepartment varchar(20) not null,
dcon varchar(20) not null
);

create table patient(
pnum varchar(20) primary key,
pname varchar(50) not null,
psex varchar(10) check(psex in ('ÄĞ','Å®')),
page int check(page >= 0 and page <= 100),
pid char(18) unique,
ptel char(11) not null,
ppsd varchar(20) not null,
pinfor varchar(500) not null,
pmdad varchar(500)
);

create table appointment(
anum int primary key,
pnum varchar(20) not null,
ddepartment varchar(20) not null,
dnum varchar(20) not null,
yytime datetime not null,
jztime datetime not null,
dcon varchar(20) not null,
astate int not null,
atype int not null,
foreign key(pnum) references patient(pnum)
on delete cascade
on update cascade,
foreign key(dnum) references doctor(dnum)
on delete cascade
on update cascade
);

create table drug(
drnum varchar(20) primary key,
drname varchar(50) not null,
drprice float not null,
drtype varchar(20) not null
);

create table takemedicine(
tmnum int primary key,
pnum varchar(20) not null,
drnum varchar(20) not null,
drquantity int not null,
tmstate int not null,
foreign key(pnum) references patient(pnum)
on delete cascade
on update cascade,
foreign key(drnum) references drug(drnum)
on delete cascade
on update cascade
);

create table medicaltec(
mtnum varchar(20) primary key,
mtname varchar(50) not null,
mtprice float not null,
mtplace varchar(20) not null
);

create table medicaltecexam(
mtenum int primary key,
pnum varchar(20) not null,
mtnum varchar(20) not null,
mtetime datetime not null,
mtstate int not null,
foreign key(pnum) references patient(pnum)
on delete cascade
on update cascade,
foreign key(mtnum) references medicaltec(mtnum)
on delete cascade
on update cascade
);