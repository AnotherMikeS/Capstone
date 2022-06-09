drop database if exists twelve_eyes_nintendo_audition;
create database twelve_eyes_nintendo_audition;
use twelve_eyes_nintendo_audition;

create table `user` (
	user_id int primary key auto_increment,
    access_type enum('auditionee', 'manager'),
    first_name char(50),
    last_name char(50)
);

create table auditionee (
	auditionee_id int primary key auto_increment,
    user_id int,
    part_id int,
    `date` date,
    selection char(100)
    );
    
create table manager (
	manager_id int primary key auto_increment,
    user_id int
);

create table part (
	part_id int primary key auto_increment,
    `role` enum('singing', 'acting')
);

create table auditions (
	audition_id int primary key auto_increment,
    auditiontee_id int,
    part_id int
);
    
    
    
    
    
    