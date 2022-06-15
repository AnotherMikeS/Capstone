use twelve_eyes_nintendo_audition;

select
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

