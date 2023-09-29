-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET UTF8MB4 ;
USE `mydb` ;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT,
  `name` VARCHAR(255) NULL,
  `lastname` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `mydb`.`sale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`sale` (
  `id` INT,
  `total_cost` DECIMAL(10,2) NULL,
  `total_quantity` INT NULL,
  `payment_type` VARCHAR(20) NULL,
  `delivery_type` VARCHAR(20) NULL,
  `sale_date` DATE NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sale_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_sale_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`delivery` (
  `id` INT,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `street` VARCHAR(255) NULL,
  `number` VARCHAR(255) NULL,
  `postalcode` VARCHAR(255) NULL,
  `status` VARCHAR(45) NULL,
  `sale_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_delivery_sale1_idx` (`sale_id` ASC) VISIBLE,
  CONSTRAINT `fk_delivery_sale1`
    FOREIGN KEY (`sale_id`)
    REFERENCES `mydb`.`sale` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`payment` (
  `id` INT,
  `name_card` VARCHAR(255) NULL,
  `cvv` VARCHAR(255) NULL,
  `card_number` VARCHAR(255) NULL,
  `expiration_date` DATE NULL,
  `sale_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_payment_sale1_idx` (`sale_id` ASC) VISIBLE,
  CONSTRAINT `fk_payment_sale1`
    FOREIGN KEY (`sale_id`)
    REFERENCES `mydb`.`sale` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`publisher` (
  `id` INT,
  `name` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `mydb`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`book` (
  `id` INT,
  `isbn` VARCHAR(255) NULL,
  `title` VARCHAR(255) NULL,
  `pages` INT NULL,
  `release_year` INT NULL,
  `book_cover` VARCHAR(255) NULL,
  `stock` INT NULL,
  `synopsis` MEDIUMTEXT NULL,
  `price` DECIMAL(10,2) NULL,
  `publisher_id` INT NOT NULL,
  PRIMARY KEY (`id`, `publisher_id`),
  INDEX `fk_book_publisher1_idx` (`publisher_id` ASC) VISIBLE,
  CONSTRAINT `fk_book_publisher1`
    FOREIGN KEY (`publisher_id`)
    REFERENCES `mydb`.`publisher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`sale_book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`sale_book` (
  `book_id` INT NOT NULL,
  `sale_id` INT NOT NULL,
  `amount` INT NULL,
  PRIMARY KEY (`book_id`, `sale_id`),
  INDEX `fk_sale_book_sale1_idx` (`sale_id` ASC) VISIBLE,
  CONSTRAINT `fk_sale_book_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sale_book_sale1`
    FOREIGN KEY (`sale_id`)
    REFERENCES `mydb`.`sale` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`genre` (
  `id` INT,
  `name` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `mydb`.`book_genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`book_genre` (
  `genre_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  PRIMARY KEY (`genre_id`, `book_id`),
  INDEX `fk_book_genre_book1_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `fk_book_genre_genre1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `mydb`.`genre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_genre_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`author` (
  `id` INT,
  `name` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `mydb`.`book_author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`book_author` (
  `book_id` INT NOT NULL,
  `author_id` INT NOT NULL,
  PRIMARY KEY (`book_id`, `author_id`),
  INDEX `fk_book_author_book1_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `fk_book_author_author1`
    FOREIGN KEY (`author_id`)
    REFERENCES `mydb`.`author` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_author_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
