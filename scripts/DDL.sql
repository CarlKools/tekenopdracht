create database if not exists drawingtool;

use drawingtool;

drop table if exists drawingtool;
drop table if exists drawing;

drop table if exists oval;
drop table if exists paintedtext;
drop table if exists image;
drop table if exists polygon;
drop table if exists vertex;

create table drawing(
id int not null auto_increment primary key,
name varchar(255) not null);

create table drawingtool(
id int not null auto_increment,
color int not null,
drawingid int default 1 not null,
constraint fk foreign key(drawingid) references drawing(id),
primary key(id));

create table oval(
weight double not null,
anchorx double not null,
anchory double not null,
width double not null,
height double not null,
drawingtoolid int not null references drawingtool(id));

create table paintedtext(
content varchar(255) not null,
fontname varchar(255) not null,
anchorx double not null,
anchory double not null,
width double not null,
height double not null,
drawingtoolid int not null references drawingtool(id));

create table image(
filepath varchar(255) not null,
anchorx double not null,
anchory double not null,
width double not null,
height double not null,
drawingtoolid int not null references drawingtool(id));

create table polygon(
id int auto_increment primary key,
weight double not null,
drawingtoolid int not null references drawingtool(id));

create table vertex(
x double not null,
y double not null,
polygonid int not null references polygon(id))