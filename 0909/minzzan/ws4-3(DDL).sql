-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ssafybook
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafybook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafybook` DEFAULT CHARACTER SET utf8 ;
USE `ssafybook` ;

-- -----------------------------------------------------
-- Table `ssafybook`.`book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafybook`.`book` ;

CREATE TABLE IF NOT EXISTS `ssafybook`.`book` (
  `isbn` INT NOT NULL,
  `author` VARCHAR(20) NULL,
  `title` VARCHAR(45) NOT NULL,
  `price` INT NULL,
  `desc` VARCHAR(45) NULL,
  `img` VARCHAR(45) NULL,
  PRIMARY KEY (`isbn`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafybook`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafybook`.`user` ;

CREATE TABLE IF NOT EXISTS `ssafybook`.`user` (
  `id` VARCHAR(20) NOT NULL,
  `name` VARCHAR(20) NULL,
  `pass` INT NULL,
  `rec_id` VARCHAR(20) NULL,
  `rec_name` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafybook`.`satisfaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafybook`.`satisfaction` ;

CREATE TABLE IF NOT EXISTS `ssafybook`.`satisfaction` (
  `isbn` INT NOT NULL,
  `rating` INT NULL,
  `comment` VARCHAR(45) NULL,
  `id` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`isbn`, `id`),
  INDEX `satisfaction_id_idx` (`id` ASC) VISIBLE,
  CONSTRAINT `satisfaction_isbn`
    FOREIGN KEY (`isbn`)
    REFERENCES `ssafybook`.`book` (`isbn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `satisfaction_id`
    FOREIGN KEY (`id`)
    REFERENCES `ssafybook`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
