-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ssafydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafydb` DEFAULT CHARACTER SET utf8 ;
USE `ssafydb` ;

-- -----------------------------------------------------
-- Table `ssafydb`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafydb`.`product` ;

CREATE TABLE IF NOT EXISTS `ssafydb`.`product` (
  `product_id` INT NOT NULL,
  `product_name` VARCHAR(20) NULL,
  `prodcut_price` INT NULL,
  PRIMARY KEY (`product_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafydb`.`user` ;

CREATE TABLE IF NOT EXISTS `ssafydb`.`user` (
  `user_id` INT NOT NULL,
  `user_name` VARCHAR(45) NULL,
  `user_address` VARCHAR(45) NULL,
  `user_phonenumber1` VARCHAR(45) NULL,
  `user_phonenumber2` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafydb`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafydb`.`order` ;

CREATE TABLE IF NOT EXISTS `ssafydb`.`order` (
  `order_id` INT NOT NULL,
  `user_id` INT NULL,
  `order_price` INT NULL,
  `order_payment` TINYINT NULL,
  `order_deliver` TINYINT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `order_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `order_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafydb`.`orderdetail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ssafydb`.`orderdetail` ;

CREATE TABLE IF NOT EXISTS `ssafydb`.`orderdetail` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `count` INT NULL,
  PRIMARY KEY (`order_id`, `product_id`),
  INDEX `orderdetail_product_id_fk_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `orderdetail_product_id_fk`
    FOREIGN KEY (`product_id`)
    REFERENCES `ssafydb`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `orderdetail_order_id_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `ssafydb`.`order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
