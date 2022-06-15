drop database if exists twelve_eyes_nintendo_audition;
create database twelve_eyes_nintendo_audition;
use twelve_eyes_nintendo_audition;

create table app_user (
	app_user_id int primary key auto_increment,
    username varchar(100) not null unique,
    password_hash varchar(2048) not null,
    enabled boolean not null default(1)    
);

create table app_role (
    app_role_id int primary key auto_increment,
    access_type varchar(50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
         references app_user(app_user_id),
	constraint fk_app_user_role_role_id
         foreign key (app_role_id)
         references app_role(app_role_id)
);

create table person (
	person_id int primary key auto_increment,
    app_user_id int not null,
	first_name varchar(50) not null,
    last_name varchar(50) not null,
    foreign key (app_user_id)
		references app_user(app_user_id)
);

create table part (
	part_id int primary key auto_increment,
    `role` enum('singing', 'acting')
);

create table available_times (
	time_id int primary key auto_increment,
    available_time varchar(50)
);
    
create table auditionee (
	auditionee_id int primary key auto_increment,
    app_user_id int not null,
    part_id int not null,
    time_slot varchar(50),
    selection varchar(100),
	foreign key(app_user_id)
		references app_user(app_user_id),
	foreign key(part_id)
		references part(part_id)
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
    
    
    
    
    
    