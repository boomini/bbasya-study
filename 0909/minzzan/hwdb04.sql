-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hworder
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hworder
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hworder` DEFAULT CHARACTER SET utf8 ;
USE `hworder` ;

-- -----------------------------------------------------
-- Table `hworder`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hworder`.`product` ;

CREATE TABLE IF NOT EXISTS `hworder`.`product` (
  `pcode` INT NOT NULL,
  `pname` VARCHAR(45) NULL,
  `price` VARCHAR(45) NULL,
  PRIMARY KEY (`pcode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hworder`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hworder`.`order` ;

CREATE TABLE IF NOT EXISTS `hworder`.`order` (
  `ordernum` INT NOT NULL,
  `oprice` INT NULL,
  `isPayed` VARCHAR(20) NULL,
  `isDel` VARCHAR(20) NULL,
  `pcode` INT NOT NULL,
  `num` INT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`ordernum`, `pcode`, `user_id`),
  INDEX `order_pcode_idx` (`pcode` ASC) VISIBLE,
  CONSTRAINT `order_pcode`
    FOREIGN KEY (`pcode`)
    REFERENCES `hworder`.`product` (`pcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hworder`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hworder`.`user` ;

CREATE TABLE IF NOT EXISTS `hworder`.`user` (
  `id` INT NOT NULL,
  `name` VARCHAR(20) NULL,
  `address` VARCHAR(20) NULL,
  `phone1` VARCHAR(20) NULL,
  `phone2` VARCHAR(20) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`id`)
    REFERENCES `hworder`.`order` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
