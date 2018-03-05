create database if not exists drawingtool;

use drawingtool;

drop table if exists drawing;
drop table if exists drawingtool;
drop table if exists oval;
drop table if exists paintedtext;
drop table if exists image;
drop table if exists polygon;
drop table if exists vertex;

create table drawing(
id int auto_increment primary key,
name varchar(255) not null);

create table drawingtool(
id int auto_increment primary key,
color int not null,
drawingid int not null references drawing(id));

create table oval(
weight double not null,
anchorx double not null,
anchory double not null,
width double not null,
height double not null,
drawingtooid double not null references drawingtool(id));

create table paintedtext(
content varchar(255) not null,
fontname varchar(255) not null,
anchorx double not null,
anchory double not null,
width double not null,
height double not null,
drawingtooid double not null references drawingtool(id));

create table image(
filepath varchar(255) not null,
anchorx double not null,
anchory double not null,
width double not null,
height double not null,
drawingtooid double not null references drawingtool(id));

create table polygon(
id int auto_increment primary key,
weight double not null,
drawingtooid double not null references drawingtool(id));

create table vertex(
x double not null,
y double not null,
polygonid double not null references polygon(id))