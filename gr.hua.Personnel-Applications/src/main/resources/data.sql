SET foreign_key_checks = 0;

INSERT INTO `department`(dep_name,super_id_fk)
    select t.*
    from ((SELECT 'IT' as col1, 2 as col2) union all
          (SELECT 'Payroll Department', 3) union all
          (SELECT 'Human Resources', 4) union all
          (SELECT 'Marketing', 5)
         ) t
    WHERE NOT EXISTS (SELECT * FROM `department`);

INSERT INTO user(username,password, emp_id)
	select t.*
	from((SELECT 'Manager' as col1, '$2y$12$CgaJlvwYCB8kJfuk8fL68eBI2JQjxvvrXu4fpEK..WOvluvkOqPI6' as col2, 1 as col3) union all
		(SELECT 'Supervisor1', '$2y$12$CgaJlvwYCB8kJfuk8fL68eBI2JQjxvvrXu4fpEK..WOvluvkOqPI6',2) union all
		(SELECT 'Supervisor2', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC',3) union all
		(SELECT 'Supervisor3', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC',4) union all
		(SELECT 'Supervisor4', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC',5) union all
		(SELECT 'Admin', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC',6) union all
		(SELECT 'It_Employee1', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC',7) union all
		(SELECT 'It_Employee2', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC',8) union all
		(SELECT 'PD_Employee1', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC',9) union all
		(SELECT 'PD_Employee2', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC',10) union all
		(SELECT 'HR_Employee1', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC',11) union all
		(SELECT 'HR_Employee2', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC',12) union all
		(SELECT 'M_Employee1', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC',13) union all
		(SELECT 'M_Employee2', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC',14)
		)t
	WHERE NOT EXISTS(SELECT * FROM `user`);

INSERT INTO authorities(username,authority)
	select t.*
	from((SELECT 'Manager' as col1, 'ROLE_MANAGER' as col2) union all
		(SELECT 'Supervisor1','ROLE_SUPERVISOR') union all
		(SELECT 'Supervisor2','ROLE_SUPERVISOR') union all
		(SELECT 'Supervisor3','ROLE_SUPERVISOR') union all
		(SELECT 'Supervisor4','ROLE_SUPERVISOR') union all
		(SELECT 'Admin','ROLE_ADMIN') union all
		(SELECT 'It_Employee1','ROLE_EMPLOYEE') union all
		(SELECT 'It_Employee2','ROLE_EMPLOYEE') union all
		(SELECT 'PD_Employee1','ROLE_PDEMPLOYEE') union all
		(SELECT 'PD_Employee2','ROLE_PDEMPLOYEE') union all
		(SELECT 'HR_Employee1','ROLE_EMPLOYEE') union all
		(SELECT 'HR_Employee2','ROLE_EMPLOYEE') union all
		(SELECT 'M_Employee1','ROLE_EMPLOYEE') union all
		(SELECT 'M_Employee2','ROLE_EMPLOYEE')
		)t
	WHERE NOT EXISTS(SELECT * FROM `authorities`);			

INSERT INTO employee(f_name,l_name,email,phone,address,birth_date,hire_date,dep_id_fk,username_fk)
	select t.*
	from((SELECT 'Jane' as col1,'Doe' as col2,'manager@gmail.com' as col3,'6909090901' as col4,'Somewhere on the map' as col5,'1999-05-20' as col6,'2020-12-10' as col7, null as col8,'Manager' as col9) union all
		(SELECT 'Jane','Doe','super1@gmail.com','6909090902','Somewhere on the map','1999-09-23','2020-12-10', 1, 'Supervisor1') union all
		(SELECT 'Jane','Doe','super2@gmail.com','6909090903','Somewhere on the map','1999-09-23','2020-12-10', 2, 'Supervisor2') union all
		(SELECT 'Jane','Doe','super3@gmail.com','6909090904','Somewhere on the map','1999-09-23','2020-12-10', 3, 'Supervisor3') union all
		(SELECT 'Jane','Doe','super4@gmail.com','6909090905','Somewhere on the map','1999-09-23','2020-12-10', 4, 'Supervisor4') union all
		(SELECT 'Jane','Doe','admin@gmail.com','6909090906','Somewhere on the map','1999-09-23','2020-12-10', 1, 'Admin') union all
		(SELECT 'Jane','Doe','it1@gmail.com','6909090907','Somewhere on the map','1999-09-23','2020-12-10', 1, 'It_Employee1') union all
		(SELECT 'Jane','Doe','it2@gmail.com','6909090908','Somewhere on the map','1999-09-23','2020-12-10', 1, 'It_Employee2') union all
		(SELECT 'Jane','Doe','pd1@gmail.com','6909090909','Somewhere on the map','1999-09-23','2020-12-10', 2, 'PD_Employee1') union all
		(SELECT 'Jane','Doe','pd2@gmail.com','6909090910','Somewhere on the map','1999-09-23','2020-12-10', 2, 'PD_Employee2') union all
		(SELECT 'Jane','Doe','hr1@gmail.com','6909090911','Somewhere on the map','1999-09-23','2020-12-10', 3, 'HR_Employee1') union all
		(SELECT 'Jane','Doe','hr2@gmail.com','6909090912','Somewhere on the map','1999-09-23','2020-12-10', 3, 'HR_Employee2') union all
		(SELECT 'Jane','Doe','m1@gmail.com','6909090913','Somewhere on the map','1999-09-23','2020-12-10', 4, 'M_Employee1') union all
		(SELECT 'Jane','Doe','m2@gmail.com','6909090914','Somewhere on the map','1999-09-23','2020-12-10', 4, 'M_Employee2')
		)t
	WHERE NOT EXISTS(SELECT * FROM `employee`);

INSERT INTO application(type,days,start_date,last_date,emp_id_fk)
	select t.*
	from((SELECT 'recovery' as col1, 3 as col2, '2020-12-17' as col3, '2020-12-20' as col4, 12 as col5) union all
		(SELECT 'strike',2,'2020-12-12','2020-12-14',5) union all
		(SELECT 'recovery',10,'2020-12-05','2020-12-14',11) union all
		(SELECT 'student',2,'2020-12-12','2020-12-14',7)
		)t
	WHERE NOT EXISTS(SELECT * FROM `application`);

SET foreign_key_checks = 1;