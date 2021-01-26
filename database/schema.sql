DROP SCHEMA IF EXISTS `rename_base`;

CREATE SCHEMA IF NOT EXISTS `rename_base`;

USE `rename_base`;

CREATE TABLE `users` (
	`user_id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NUll UNIQUE,
    `name` VARCHAR(255) NOT NULL,
    `surname` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role` ENUM('User', 'Admin'),
    `enabled` BOOLEAN NOT NULL
);

INSERT INTO `users` (`email`, `name`, `surname`, `password`, `role`, `enabled`)
 VALUES ('maxim.style@mail.ru', 'Maksim', 'Zhabinko', 'TWFrc21hbjE0Nzg5NjMyQA==', 'admin', true),
 ('m.style@mail.ru', 'Vlad', 'Sakovich', 'TWFrc21hbjE0Nzg5NjMyQA==', 'user', true),
 ('m.fd@mail.ru', 'Dima', 'Stalchenko', 'TWFrc21hbjE0Nzg5NjMyQA==', 'user', true);
