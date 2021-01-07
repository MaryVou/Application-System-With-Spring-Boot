SET foreign_key_checks = 0;

INSERT INTO `department`(dep_name,super_id_fk)
    select t.*
    from ((SELECT 'Secretariat' as col1, 2 as col2) union all
          (SELECT 'IT', 1)
         ) t
    WHERE NOT EXISTS (SELECT * FROM `department`);

INSERT INTO user(username,password, emp_id)
	select t.*
	from((SELECT 'MaryVou' as col1, '$2y$12$CgaJlvwYCB8kJfuk8fL68eBI2JQjxvvrXu4fpEK..WOvluvkOqPI6' as col2, 1 as col3) union all
		(SELECT 'ElliKal', '$2y$12$CgaJlvwYCB8kJfuk8fL68eBI2JQjxvvrXu4fpEK..WOvluvkOqPI6',2) union all
		(SELECT 'NikakiTreloGavraki', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC',3)
		)t
	WHERE NOT EXISTS(SELECT * FROM `user`);

INSERT INTO authorities(username,authority)
	select t.*
	from((SELECT 'MaryVou' as col1, 'ROLE_ADMIN' as col2) union all
		(SELECT 'ElliKal','ROLE_SUPERVISOR') union all
		(SELECT 'NikakiTreloGavraki','ROLE_EMPLOYEE')
		)t
	WHERE NOT EXISTS(SELECT * FROM `authorities`);			

INSERT INTO employee(f_name,l_name,email,phone,address,birth_date,hire_date,dep_id_fk,username_fk)
	select t.*
	from((SELECT 'Mary' as col1,'Voulieri' as col2,'it21710@gmail.com' as col3,'6906969696' as col4,'Steriperi' as col5,'1999-05-20' as col6,'2020-12-10' as col7, 2 as col8,'MaryVou' as col9) union all
		(SELECT 'Elli','Kalavrytinou','it21721@gmail.com','6906969669','Kifisia','1999-09-23','2020-12-10', 1, 'ElliKal') union all
		(SELECT 'Niki','Anagnostopoulou','pada@gmail.com','6906969696','Steriperi','1999-10-28','2020-12-10', 1,'NikakiTreloGavraki')
		)t
	WHERE NOT EXISTS(SELECT * FROM `employee`);

INSERT INTO application(type,days,start_date,last_date,emp_id_fk)
	select t.*
	from((SELECT 'recovery' as col1, 3 as col2, '2020-12-17' as col3, '2020-12-20' as col4, 1 as col5) union all
		(SELECT 'strike',2,'2020-12-12','2020-12-14',2) union all
		(SELECT 'student',2,'2020-12-12','2020-12-14',3)
		)t
	WHERE NOT EXISTS(SELECT * FROM `application`);

SET foreign_key_checks = 1;