use twelve_eyes_nintendo_audition;

select * from person;

select * from auditions;

select * from auditionee;


select * from app_user_role;

select * from app_role;

update app_user_role set 
	app_role_id = 1
    where app_user_id = 1;

select * from app_user;

select
	auditions.audition_id,
    auditions.auditionee_id,
    auditionee.auditionee_id,
    person.first_name,
    person.last_name,
    part.part_id,
    part.`role`,
    auditionee.selection
from auditionee
inner join app_user on auditionee.app_user_id = app_user.app_user_id
inner join person on app_user.app_user_id = person.app_user_id
inner join auditions on auditionee.auditionee_id = auditions.auditionee_id
inner join part on auditions.part_id = part.part_id;

select * from app_user;

select
	auditions.audition_id,
	auditionee.auditionee_id,
    person.first_name,
    person.last_name,
	part.`role`,
    auditionee.selection
from auditionee
inner join app_user on auditionee.app_user_id = app_user.app_user_id
inner join person on app_user.app_user_id = person.app_user_id
inner join auditions on auditionee.auditionee_id = auditions.auditionee_id
inner join part on auditions.part_id = part.part_id;

