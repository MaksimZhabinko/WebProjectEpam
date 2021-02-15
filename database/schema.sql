DROP SCHEMA IF EXISTS `course`;

CREATE SCHEMA IF NOT EXISTS `course`;

USE `course`;

CREATE TABLE `users` (
	`user_id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NUll UNIQUE,
    `name` VARCHAR(255) NOT NULL,
    `surname` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role` ENUM('User', 'Admin'),
    `enabled` BOOLEAN NOT NULL
);

CREATE TABLE `teachers` (
	`teacher_id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL ,
    `surname` VARCHAR(255) NOT NULL
);

CREATE TABLE `courses` (
	`course_id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `course_name` VARCHAR(255) NOT NULL
);

CREATE TABLE `course_details` (
	`course_detail_id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `number_of_hours` INTEGER NOT NULL,
    `description` TEXT NOT NULL,
    `is_test` BOOLEAN DEFAULT FALSE,
    `start_course` DATE NOT NULL,
    `end_course` DATE NOT NULL,
    `start_of_class` TIME NOT NULL,
    `cost` DECIMAL NOT NULL,

    `fk_course_id` INTEGER NOT NULL,
    `fk_teacher_name_id` INTEGER DEFAULT NULL,

    CONSTRAINT `fk_course_details_x_course` FOREIGN KEY (`fk_course_id`) REFERENCES `courses` (`course_id`)
				ON DELETE CASCADE
				ON UPDATE CASCADE,

	CONSTRAINT `fk_course_x_teacher` FOREIGN KEY (`fk_teacher_name_id`) REFERENCES `teachers` (`teacher_id`)
				ON DELETE SET NULL
                ON UPDATE CASCADE
);

CREATE TABLE `lectures` (
	`lecture_id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `lecture` VARCHAR(255),
    `fk_lecture_x_course_id` INTEGER NOT NULL,

    CONSTRAINT `fk_lecture_x_course` FOREIGN KEY (`fk_lecture_x_course_id`) REFERENCES `courses` (`course_id`)
		ON DELETE CASCADE
);

CREATE TABLE `reviews` (
	`review_id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `message` TEXT NOT NULL,
    `date_message` DATE NOT NULL,
    `fk_reviews_x_user_id` INTEGER NOT NULL,

    CONSTRAINT `fk_reviews_x_user` FOREIGN KEY (`fk_reviews_x_user_id`) REFERENCES `users`(`user_id`)
);

INSERT INTO `users` (`email`, `name`, `surname`, `password`, `role`, `enabled`)
 VALUES ('maxim.style@mail.ru', 'Maksim', 'Zhabinko', 'TWFrc21hbjE0Nzg5NjMyQA==', 'admin', true),
 ('m.style@mail.ru', 'Vlad', 'Sakovich', 'TWFrc21hbjE0Nzg5NjMyQA==', 'user', true),
 ('m.fd@mail.ru', 'Dima', 'Stalchenko', 'TWFrc21hbjE0Nzg5NjMyQA==', 'user', true);

INSERT INTO `teachers` (`name`, `surname`) VALUES ('Никита', 'Решала');
INSERT INTO `teachers`  (`name`, `surname`) VALUES ('Артем', 'Шевчюк');
INSERT INTO `teachers`  (`name`, `surname`) VALUES ('Гена', 'Мабик');

INSERT INTO `courses`  (`course_name`) VALUES ('Java SE');
INSERT INTO `courses`  (`course_name`) VALUES ('Java EE');
INSERT INTO `courses`  (`course_name`) VALUES ('Java lab');
INSERT INTO `courses` (`course_name`) VALUES ('Основы программирования на Python');
INSERT INTO `courses` (`course_name`) VALUES ('Основы программирования на C#');

INSERT INTO `course_details` (`number_of_hours`, `description`, `start_course`, `end_course`, `start_of_class`, `cost`, `fk_course_id`, `fk_teacher_name_id`) VALUES (72, 'Java SE description', '2021-03-15', '2021-06-15', '19:00', 499, 1, 1);
INSERT INTO `course_details` (`number_of_hours`, `description`, `is_test`, `start_course`, `end_course`, `start_of_class`, `cost`, `fk_course_id`, `fk_teacher_name_id`) VALUES (72, 'Java EE description', true, '2021-03-20', '2021-06-20', '19:00', 999, 2, 1);
INSERT INTO `course_details` (`number_of_hours`, `description`, `is_test`, `start_course`, `end_course`, `start_of_class`, `cost`, `fk_course_id`, `fk_teacher_name_id`) VALUES (60, 'Java lab description', true, '2021-03-25', '2021-06-25', '19:00', 1499, 3, 1);
INSERT INTO `course_details` (`number_of_hours`, `description`, `start_course`, `end_course`, `start_of_class`, `cost`,  `fk_course_id`, `fk_teacher_name_id`) VALUES (60, 'Основы программирования на Python description', '2021-04-01', '2021-07-01', '19:00', 499, 4, 2);
INSERT INTO `course_details` (`number_of_hours`, `description`, `start_course`, `end_course`, `start_of_class`, `cost`,  `fk_course_id`, `fk_teacher_name_id`) VALUES (68, 'Основы программирования на C# description', '2021-04-20', '2021-07-20', '19:00', 499, 5, 3);

INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 1. Введение в Java. Основы языка.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 2. Операторы управления. Циклы.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 3. Класс Math. Случайные числа. Массивы.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 4. Классы и объекты.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 5. Работа с библиотечными классами.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 6. Наследование.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 7. Object. Интерфейсы.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 8. Обработка исключительных ситуаций.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 9. Универсальные типы.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 10. Коллекции.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 11. Работа с датой и временем. Рефлексия.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 12. Основы баз данных.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 13. JDBC: Программирование баз данных в java.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 14. Знакомство с SWING.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 15. Элементы библиотеки SWING. Обработка событий.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 16. Написание итогового проекта.', 1);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 17. Итоговое занятие.', 1);

INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 1. Введение.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 2. Введение в Web-программирование. Основные технологии.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 3. Введение в Web-программирование на Java.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 4. Servlets API.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 5. Servlet API – окончание.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 6. Введение в теорию баз данных.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 7. Введение в JDBC API.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 8. Hibernate.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 9. Hibernate.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 10. Java Server Pages.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 11. JSP Model-2 и MVC шаблон.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 12. Spring MVC.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 13. Spring MVC.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 14. Spring MVC.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 15. Логирование, интернационализация.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 16. Sokets, WebSokets.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 17. Основы Linux.', 2);
INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES ('Занятие 18. Последнее занятие.', 2);



INSERT INTO `reviews` (`message`, `date_message`, `fk_reviews_x_user_id`) VALUES ('прошел java SE все поравилось!', '2008-11-11', 2);
INSERT INTO `reviews` (`message`, `date_message`, `fk_reviews_x_user_id`) VALUES ('прошел java SE все поравилось! НО пздц сложно', '2008-11-11', 2);
INSERT INTO `reviews` (`message`, `date_message`, `fk_reviews_x_user_id`) VALUES ('прошел java EE все поравилось!', '2008-11-11', 3);
