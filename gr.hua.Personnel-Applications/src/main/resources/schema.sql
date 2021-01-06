SET foreign_key_checks = 1;

CREATE TABLE IF NOT EXISTS `user`(
	`username` varchar(50) NOT NULL UNIQUE,
	`password` varchar(100) NOT NULL,
	`enabled` tinyint DEFAULT 1,
	`emp_id` int,
	PRIMARY KEY(`username`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE IF NOT EXISTS `authorities`(
  `username` varchar(50) NOT NULL UNIQUE,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_user` FOREIGN KEY(`username`) REFERENCES `user`(`username`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE IF NOT EXISTS `employee`(
	`emp_id` int AUTO_INCREMENT,
	`f_name` varchar(20),
	`l_name` varchar(20),
	`email` varchar(30),
	`phone` varchar(15),
	`address` varchar(30),
	`birth_date` date,
	`hire_date` date,
	`dep_id_fk` int,
	`username_fk` varchar(50),
	PRIMARY KEY(`emp_id`),
	CONSTRAINT `fk_employee_user` FOREIGN KEY(`username_fk`) REFERENCES `user`(`username`) ON DELETE SET NULL
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

ALTER TABLE `user` ADD CONSTRAINT `fk_user_employee` FOREIGN KEY(`emp_id`) REFERENCES `employee`(`emp_id`) ON DELETE CASCADE;

CREATE TABLE IF NOT EXISTS `department`(
	`dep_id` int NOT NULL AUTO_INCREMENT,
	`dep_name` varchar(20),
	`super_id_fk` int,
	PRIMARY KEY(`dep_id`),
	CONSTRAINT `fk_department_employee` FOREIGN KEY(`super_id_fk`) REFERENCES `employee`(`emp_id`) ON DELETE SET NULL
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

ALTER TABLE `employee` ADD CONSTRAINT `fk_employee_department` FOREIGN KEY(`dep_id_fk`) REFERENCES `department`(`dep_id`) ON DELETE SET NULL;

CREATE TABLE IF NOT EXISTS `application`(
	`app_id` int NOT NULL AUTO_INCREMENT,
	`type` varchar(15),
	`days` int,
	`start_date` date,
	`last_date` date,
	`req_papers` blob,
	`super_sig` tinyint,
	`pd_sig` tinyint,
	`mgr_sig` tinyint,
	`emp_id_fk` int,
	PRIMARY KEY(`app_id`),
	CONSTRAINT `fk_application_employee` FOREIGN KEY(`emp_id_fk`) REFERENCES `employee`(`emp_id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;