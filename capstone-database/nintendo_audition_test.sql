drop database if exists nintendo_audition_test;
create database nintendo_audition_test;
use nintendo_audition_test;

create table app_user (
	app_user_id int primary key auto_increment,
    first_name varchar(50),
    last_name varchar(50),
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
    user_id int not null,
    part_id int not null,
    time_slot varchar(50),
    selection varchar(100),
     foreign key(user_id)
    references `user`(user_id),
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

delimiter //
create procedure set_known_good_state()
begin

    delete from auditions;
    alter table auditions auto_increment = 1;
    
	delete from manager;
    alter table manager auto_increment = 1;
    
	delete from auditionee;
    alter table auditionee auto_increment = 1;
    
    delete from available_times;
    alter table available_times auto_increment = 1;
    
	delete from part;
	alter table part auto_increment = 1;

	delete from app_user;
    alter table app_user auto_increment = 1;
           
    insert into app_user (app_user_id, username, password_hash, first_name, last_name) values
		(1, 'sNixon', 'password', 'Shelley', 'Nixon'),
		(2, 'mSmith', 'password', 'Michael', 'Smith'),
        (3, 'gGennaro', 'password', 'Greg', 'Gennaro'),
        (4, 'mMario', 'password', 'Mario', 'Mario');
        
	insert into app_role (app_role_id, access_type) values
		(1, 'manager'),
        (2, 'auditionee');
        
	insert into app_user_role (app_user_id, app_role_id) values
		(1, 2),
        (2, 2),
        (3, 1),
        (4, 2);
    
    insert into part (part_id, `role`) values
        (1, 'singing'),
        (2, 'acting');
        
	insert into available_times (time_id, available_time) values
        (3, '2022-07-01 1:00pm'),
        (4, '2022-07-02 2:00pm');
        
	insert into auditionee (auditionee_id, app_user_id, part_id, time_slot, selection) values
	(1, 1, 2, '2022-07-01 12:00pm', 'Kristin Monologue'),
    (2, 2, 1, '2022-07-02 1:00pm', 'Its a cold and its a broken Waluigi'),
    (3, 4, 1, '2022-07-02 12:20pm', 'On My Way');
         
	insert into auditions (audition_id, auditionee_id, part_id) values
        (1, 1, 2),
        (2, 2, 1);
end //
delimiter ;