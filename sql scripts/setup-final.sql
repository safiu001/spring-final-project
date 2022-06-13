CREATE DATABASE  IF NOT EXISTS `spring_final`;
USE `spring_final`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  constraint unique (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

-- INSERT INTO `users` 
-- VALUES 
-- ('john','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
-- ('mary','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
-- ('susan','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `auth_id` int NOT NULL auto_increment,
  `username` varchar(20) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `username_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `auth_pk` primary key (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- list of movies
DROP TABLE IF EXISTS `movie_list`;
CREATE TABLE `movie_list` (
    `id` varchar(20) NOT NULL auto_increment,
    `name` varchar(100),
    `description` text,
    `category` varchar(50),
    constraint `name_pk` primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- customer's list of selected movies
DROP TABLE IF EXISTS `customer_list`;
CREATE TABLE `customer_list` (
	`user_id` int NOT NULL,
    `movie_id` varchar(20) NOT NULL,
    constraint `cutomer_list_pk` primary key(`movie_id`, `user_id`),
    constraint `username_fk` foreign key(`user_id`) references `users` (`id`),
    constraint `movie_fk` foreign key(`movie_id`) references `movie_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

-- INSERT INTO `authorities` 
-- VALUES 
-- ('john','ROLE_EMPLOYEE'),
-- ('mary','ROLE_EMPLOYEE'),
-- ('mary','ROLE_MANAGER'),
-- ('susan','ROLE_EMPLOYEE'),
-- ('susan','ROLE_ADMIN');


