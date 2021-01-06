INSERT INTO department(dep_name) VALUES ('Secretariat'), ('IT');#, ('Payroll'), ('Marketing'), ('Human Resources');

INSERT INTO user(username,password) VALUES
	('MaryVou', '$2y$12$CgaJlvwYCB8kJfuk8fL68eBI2JQjxvvrXu4fpEK..WOvluvkOqPI6'),
	('ElliKal', '$2y$12$CgaJlvwYCB8kJfuk8fL68eBI2JQjxvvrXu4fpEK..WOvluvkOqPI6'),
	('NikakiTreloGavraki', '$2y$12$Ml7Mf8OIZOPI.wynlTmz4OV6dM5Z9WaOJQELq/0kVoufTy4ni9/DC');

INSERT INTO authorities(username,authority) VALUES
	('MaryVou','ROLE_ADMIN'),
	('ElliKal','ROLE_SUPERVISOR'),
	('NikakiTreloGavraki','ROLE_EMPLOYEE');				

INSERT INTO employee(f_name,l_name,email,phone,address,birth_date,hire_date,dep_id_fk,username_fk) VALUES
	('Mary','Voulieri','it21710@gmail.com','6906969696','Steriperi','1999-05-20','2020-12-10', 2,'MaryVou'),
	('Elli','Kalavrytinou','it21721@gmail.com','6906969669','Kifisia','1999-09-23','2020-12-10', 1, 'ElliKal'),
	('Niki','Anagnostopoulou','pada@gmail.com','6906969696','Steriperi','1999-10-28','2020-12-10', 1,'NikakiTreloGavraki');

UPDATE department SET super_id_fk=2 WHERE dep_name = "Secretariat";
UPDATE department SET super_id_fk=1 WHERE dep_name = "IT";

UPDATE user INNER JOIN employee ON user.username = employee.username_fk SET user.emp_id = employee.emp_id;

INSERT INTO application(type,days,start_date,last_date,emp_id_fk) VALUES
	('recovery',3,'2020-12-17','2020-12-20',1),
	('strike',2,'2020-12-12','2020-12-14',2),
	('student',2,'2020-12-12','2020-12-14',3);