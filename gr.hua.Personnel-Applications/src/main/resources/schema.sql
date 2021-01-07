SET foreign_key_checks = 0;

CREATE TABLE IF NOT EXISTS `user`(
	`username` varchar(50) NOT NULL UNIQUE,
	`password` varchar(100) NOT NULL,
	`enabled` tinyint DEFAULT 1,
	`emp_id` int,
	PRIMARY KEY(`username`),
	CONSTRAINT `fk_user_employee` FOREIGN KEY(`emp_id`) REFERENCES `employee`(`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE IF NOT EXISTS `authorities`(
  `username` varchar(50) NOT NULL UNIQUE,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_user` FOREIGN KEY(`username`) REFERENCES `user`(`username`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE IF NOT EXISTS `employee`(
	`emp_id` int AUTO_INCREMENT,
	`f_name` varchar(20) NOT NULL,
	`l_name` varchar(20) NOT NULL,
	`email` varchar(30) NOT NULL UNIQUE,
	`phone` varchar(15) NOT NULL UNIQUE,
	`address` varchar(30) NOT NULL,
	`birth_date` date NOT NULL,
	`hire_date` date NOT NULL,
	`dep_id_fk` int,
	`username_fk` varchar(50),
	PRIMARY KEY(`emp_id`),
	CONSTRAINT `fk_employee_user` FOREIGN KEY(`username_fk`) REFERENCES `user`(`username`) ON DELETE SET NULL ON UPDATE CASCADE,
	CONSTRAINT `fk_employee_department` FOREIGN KEY(`dep_id_fk`) REFERENCES `department`(`dep_id`) ON DELETE SET NULL ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE IF NOT EXISTS `department`(
	`dep_id` int NOT NULL AUTO_INCREMENT,
	`dep_name` varchar(20) NOT NULL UNIQUE,
	`super_id_fk` int,
	PRIMARY KEY(`dep_id`),
	CONSTRAINT `fk_department_employee` FOREIGN KEY(`super_id_fk`) REFERENCES `employee`(`emp_id`) ON DELETE SET NULL ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE IF NOT EXISTS `application`(
	`app_id` int NOT NULL AUTO_INCREMENT,
	`type` varchar(15) NOT NULL,
	`days` int NOT NULL,
	`start_date` date NOT NULL,
	`last_date` date NOT NULL,
	`req_papers` blob,
	`super_sig` tinyint,
	`pd_sig` tinyint,
	`mgr_sig` tinyint,
	`emp_id_fk` int,
	PRIMARY KEY(`app_id`),
	CONSTRAINT `fk_application_employee` FOREIGN KEY(`emp_id_fk`) REFERENCES `employee`(`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

SET foreign_key_checks = 1;