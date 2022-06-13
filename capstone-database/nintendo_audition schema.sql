drop database if exists twelve_eyes_nintendo_audition;
create database twelve_eyes_nintendo_audition;
use twelve_eyes_nintendo_audition;

create table `user` (
	user_id int primary key auto_increment,
    access_type enum('auditionee', 'manager'),
    first_name varchar(50),
    last_name varchar(50)
);

create table part (
	part_id int primary key auto_increment,
    `role` enum('singing', 'acting')
);

create table auditionee (
	auditionee_id int primary key auto_increment,
    user_id int not null,
    part_id int not null,
    time_slot varchar(50),
    selection varchar(100),
     foreign key(user_id)
    references `user`(user_id),
     foreign key(part_id)
    references part(part_id)
    );
    
create table manager (
	manager_id int primary key auto_increment,
    user_id int,
     foreign key(user_id)
    references `user`(user_id)
);

create table auditions (
	audition_id int primary key auto_increment,
    auditionee_id int,
    part_id int,
     foreign key(auditionee_id)
    references auditionee(auditionee_id),
     foreign key(part_id)
    references part(part_id)
);
    
    
    
    
    
    