CREATE DATABASE IF NOT EXISTS ssafit;
USE ssafit;

CREATE TABLE IF NOT EXISTS `user` (
  `userKey` INT NOT NULL AUTO_INCREMENT ,
  `id` VARCHAR(30) NOT NULL UNIQUE,
  `password` VARCHAR(30) NOT NULL,
  `userImgPath` VARCHAR(255) UNIQUE,
  `exp` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`userKey`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `study` (
  `studyKey` INT NOT NULL AUTO_INCREMENT ,
  `leaderKey` INT NOT NULL,
  `studyName` VARCHAR(100) NOT NULL UNIQUE,
  `category` VARCHAR(100) NOT NULL,
  `studyStart` TIMESTAMP NOT NULL,
  `studyEnd` TIMESTAMP NOT NULL,
  `alarm` INT,
  PRIMARY KEY (`studyKey`),
  FOREIGN KEY (`leaderKey`) REFERENCES user(`userKey`) ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `review` (
  `reviewKey` INT NOT NULL AUTO_INCREMENT ,
  `studyKey` INT NOT NULL,
  `reviewTitle` VARCHAR(255),
  `reviewContent` TEXT,
  `reviewDate` TIMESTAMP NOT NULL,
  `reviewImgPath` VARCHAR(255) UNIQUE,
  `viewCnt` INT DEFAULT 0,
  PRIMARY KEY (`reviewKey`),
  FOREIGN KEY (`studyKey`) REFERENCES study(`studyKey`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `schedule` (
  `scheduleKey` INT NOT NULL AUTO_INCREMENT ,
  `studyKey` INT NOT NULL,
  `scheduleDate` TIMESTAMP NOT NULL,
  `scheduleContent` VARCHAR(255) NOT NULL,
  `schedulePlace` VARCHAR(255) NOT NULL,
  UNIQUE (studyKey, scheduleDate, scheduleContent, schedulePlace),
  PRIMARY KEY (`scheduleKey`),
  FOREIGN KEY (`studyKey`) REFERENCES study(`studyKey`) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `todo` (
  `todoKey` INT NOT NULL AUTO_INCREMENT ,
  `studyKey` INT NOT NULL,
  `todoStart` TIMESTAMP NOT NULL,
  `todoEnd` TIMESTAMP NOT NULL,
  `todoContent` VARCHAR(255) NOT NULL,
  UNIQUE (studyKey, todoStart, todoEnd, todoContent),
  PRIMARY KEY (`todoKey`),
  FOREIGN KEY (`studyKey`) REFERENCES study(`studyKey`) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `user_todo` (
  `userKey` INT NOT NULL,
  `todoKey` INT NOT NULL,
  `success` BOOLEAN NOT NULL DEFAULT 0,
  PRIMARY KEY (`userKey`, `todoKey`),
  FOREIGN KEY (`userKey`) REFERENCES user(`userKey`) ON DELETE CASCADE,
  FOREIGN KEY (`todoKey`) REFERENCES todo(`todoKey`) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `study_user_review` (
  `studyKey` INT,
  `userKey` INT,
  `reviewKey` INT,
  PRIMARY KEY (`studyKey`, `userKey`),
  FOREIGN KEY (`studyKey`) REFERENCES study(`studyKey`) ON DELETE CASCADE,
  FOREIGN KEY (`userKey`) REFERENCES `user`(`userKey`) ON DELETE CASCADE,
  FOREIGN KEY (`reviewKey`) REFERENCES review(`reviewKey`) ON DELETE SET NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `study_schedule` (
  `studyKey` INT NOT NULL,
  `scheduleKey` INT NOT NULL,
  PRIMARY KEY (`studyKey`, `scheduleKey`),
  FOREIGN KEY (`studyKey`) REFERENCES study(`studyKey`) ON DELETE CASCADE,
  FOREIGN KEY (`scheduleKey`) REFERENCES schedule(`scheduleKey`) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `study_todo` (
  `studyKey` INT NOT NULL,
  `todoKey` INT NOT NULL,
  PRIMARY KEY (`studyKey`, `todoKey`),
  FOREIGN KEY (`studyKey`) REFERENCES study(`studyKey`) ON DELETE CASCADE,
  FOREIGN KEY (`todoKey`) REFERENCES todo(`todoKey`) ON DELETE CASCADE
) ENGINE = InnoDB;

