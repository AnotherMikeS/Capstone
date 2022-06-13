drop database if exists nintendo_audition_test;
create database nintendo_audition_test;
use nintendo_audition_test;

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

	delete from `user`;
    alter table `user` auto_increment = 1;
           
    insert into `user` (user_id, access_type, first_name, last_name) values
		(1, 'auditionee', 'Shelley', 'Nixon'),
		(2, 'auditionee', 'Michael', 'Smith'),
        (3, 'manager', 'Greg', 'Gennaro'),
        (4, 'auditionee', 'Mario', 'Mario');
    
    insert into part (part_id, `role`) values
        (1, 'singing'),
        (2, 'acting');
        
	insert into available_times (time_id, available_time) values
        (3, '2022-07-01 1:00pm'),
        (4, '2022-07-02 2:00pm');
        
	insert into auditionee (auditionee_id, user_id, part_id, time_slot, selection) values
	(1, 1, 2, '2022-07-01 12:00pm', 'Kristin Monologue'),
    (2, 2, 1, '2022-07-02 1:00pm', 'Its a cold and its a broken Waluigi'),
    (3, 4, 1, '2022-07-02 12:20pm', 'On My Way');
    
	insert into manager (manager_id, user_id) values
		(1, 3);
        
	insert into auditions (audition_id, auditionee_id, part_id) values
        (1, 1, 2),
        (2, 2, 1);
end //
delimiter ;