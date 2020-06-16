CREATE TABLE `toeic`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);
INSERT INTO `toeic`.`user` (`username`, `password`) VALUES ('admin', '$2a$10$11eEFUwakN7W.N2kv7AKteP10MI.j3r0KXEHquHwSDIS4.NjMTMMW');

  
  CREATE TABLE `toeic`.`exam` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  INSERT INTO `toeic`.`exam` (`id`, `name`) VALUES ('1', 'De 1');


CREATE TABLE `toeic`.`part` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));
  
  
INSERT INTO `toeic`.`part` (`id`, `name`) VALUES ('1', 'Photo');
INSERT INTO `toeic`.`part` (`id`, `name`) VALUES ('2', 'Question-Response');
INSERT INTO `toeic`.`part` (`id`, `name`) VALUES ('3', 'Short conversation');
INSERT INTO `toeic`.`part` (`id`, `name`) VALUES ('4', 'Short talk');
INSERT INTO `toeic`.`part` (`id`, `name`) VALUES ('5', 'Incomplete Sentences');
INSERT INTO `toeic`.`part` (`id`, `name`) VALUES ('6', 'Text Completion');
INSERT INTO `toeic`.`part` (`id`, `name`) VALUES ('7', 'Reading Comprehension');

  CREATE TABLE `toeic`.`group_question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `explain_answer` VARCHAR(2000),
  `link_image_resource` VARCHAR(100) NULL,
  `link_mp3_resource` VARCHAR(100) NULL,
  `resource_paragraph` VARCHAR(2000) NULL,
  `part_id` INT NOT NULL,
   `exam_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `group_question_part_id` (`part_id` ASC) VISIBLE,
  CONSTRAINT `group_question_part`
    FOREIGN KEY (`part_id`)
    REFERENCES `toeic`.`part` (`id`),
    CONSTRAINT `group_question_exam`
    FOREIGN KEY (`exam_id`)
    REFERENCES `toeic`.`exam` (`id`));
    
  CREATE TABLE `toeic`.`question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(2000),
  `correct_answer` INT NOT NULL,
  `explain_answer` VARCHAR(2000) NULL,
  `link_image_resource` VARCHAR(100) NULL,
  `link_mp3_resource` VARCHAR(100) NULL,
  `resource_paragraph` VARCHAR(2000) NULL,
  `group_question_id` INT ,
   `part_id` INT NOT NULL,
   `exam_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `question_part_id` (`part_id` ASC) VISIBLE,
  CONSTRAINT `question_part`
    FOREIGN KEY (`part_id`)
    REFERENCES `toeic`.`part` (`id`),
    CONSTRAINT `question_exam`
    FOREIGN KEY (`exam_id`)
    REFERENCES `toeic`.`exam` (`id`));

    CREATE TABLE `toeic`.`answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `answer` VARCHAR(255) NOT NULL,
  `question_id` INT,
  PRIMARY KEY (`id`),
  INDEX `answer_question_id_idx` (`question_id` ASC) VISIBLE,
  CONSTRAINT `answer_question_id`
    FOREIGN KEY (`question_id`)
    REFERENCES `toeic`.`question` (`id`));
