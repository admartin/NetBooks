SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema netbooks
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema netbooks
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `netbooks` DEFAULT CHARACTER SET utf8 ;
USE `netbooks` ;

-- -----------------------------------------------------
-- Table `netbooks`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `netbooks`.`Users` ;

CREATE TABLE IF NOT EXISTS `netbooks`.`Users` (
  `username` VARCHAR(12) NOT NULL,
  `password` VARCHAR(17) NOT NULL,
  `fname` VARCHAR(45) NULL,
  `lname` VARCHAR(45) NULL,
  `birthdate` DATETIME NULL,
  `address` VARCHAR(45) NULL,
  `zipcode` INT NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(2) NULL,
  `subscription` INT NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `netbooks`.`Authors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `netbooks`.`Authors` ;

CREATE TABLE IF NOT EXISTS `netbooks`.`Authors` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fname` VARCHAR(45) NULL,
  `lname` VARCHAR(45) NULL,
  `birthdate` DATETIME NULL,
  `gender` VARCHAR(1) NULL,
  `bio` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `netbooks`.`Genres`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `netbooks`.`Genres` ;

CREATE TABLE IF NOT EXISTS `netbooks`.`Genres` (
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE INDEX `type_UNIQUE` (`type` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `netbooks`.`Books`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `netbooks`.`Books` ;

CREATE TABLE IF NOT EXISTS `netbooks`.`Books` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `numCopies` INT NULL,
  `pubDate` DATETIME NULL,
  `numOut` INT NULL,
  `ebook` TINYINT(1) NULL,
  `link` VARCHAR(100) NULL,
  `rating` INT NULL,
  `Authors_id` INT UNSIGNED NOT NULL,
  `Genres_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `Authors_id`, `Genres_type`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_Books_Authors1_idx` (`Authors_id` ASC),
  INDEX `fk_Books_Genres1_idx` (`Genres_type` ASC),
  CONSTRAINT `fk_Books_Authors1`
    FOREIGN KEY (`Authors_id`)
    REFERENCES `netbooks`.`Authors` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Books_Genres1`
    FOREIGN KEY (`Genres_type`)
    REFERENCES `netbooks`.`Genres` (`type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `netbooks`.`Reviews`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `netbooks`.`Reviews` ;

CREATE TABLE IF NOT EXISTS `netbooks`.`Reviews` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` INT NULL,
  `details` VARCHAR(1000) NULL,
  `Books_id` INT NOT NULL,
  `Users_username` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`id`, `Books_id`, `Users_username`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_Reviews_Books1_idx` (`Books_id` ASC),
  INDEX `fk_Reviews_Users1_idx` (`Users_username` ASC),
  CONSTRAINT `fk_Reviews_Books1`
    FOREIGN KEY (`Books_id`)
    REFERENCES `netbooks`.`Books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reviews_Users1`
    FOREIGN KEY (`Users_username`)
    REFERENCES `netbooks`.`Users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `netbooks`.`Waitlist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `netbooks`.`Waitlist` ;

CREATE TABLE IF NOT EXISTS `netbooks`.`Waitlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Users_username` VARCHAR(12) NOT NULL,
  `Books_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Users_username`, `Books_id`),
  INDEX `fk_Waitlist_Users1_idx` (`Users_username` ASC),
  INDEX `fk_Waitlist_Books1_idx` (`Books_id` ASC),
  CONSTRAINT `fk_Waitlist_Users1`
    FOREIGN KEY (`Users_username`)
    REFERENCES `netbooks`.`Users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Waitlist_Books1`
    FOREIGN KEY (`Books_id`)
    REFERENCES `netbooks`.`Books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `netbooks`.`BooksRead`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `netbooks`.`BooksRead` ;

CREATE TABLE IF NOT EXISTS `netbooks`.`BooksRead` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Books_id` INT NOT NULL,
  `Users_username` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`id`, `Books_id`, `Users_username`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_BooksRead_Books1_idx` (`Books_id` ASC),
  INDEX `fk_BooksRead_Users1_idx` (`Users_username` ASC),
  CONSTRAINT `fk_BooksRead_Books1`
    FOREIGN KEY (`Books_id`)
    REFERENCES `netbooks`.`Books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BooksRead_Users1`
    FOREIGN KEY (`Users_username`)
    REFERENCES `netbooks`.`Users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `netbooks`.`CheckedOut`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `netbooks`.`CheckedOut` ;

CREATE TABLE IF NOT EXISTS `netbooks`.`CheckedOut` (
  `Users_username` VARCHAR(12) NOT NULL,
  `Books_id` INT NOT NULL,
  PRIMARY KEY (`Users_username`, `Books_id`),
  INDEX `fk_CheckedOut_Books1_idx` (`Books_id` ASC),
  CONSTRAINT `fk_CheckedOut_Users`
    FOREIGN KEY (`Users_username`)
    REFERENCES `netbooks`.`Users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CheckedOut_Books1`
    FOREIGN KEY (`Books_id`)
    REFERENCES `netbooks`.`Books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
