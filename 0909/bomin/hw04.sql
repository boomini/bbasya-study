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
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`customer` ;

CREATE TABLE IF NOT EXISTS `mydb`.`customer` (
  `idcustomer` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `phone1` VARCHAR(15) NULL,
  `phone2` VARCHAR(15) NULL,
  PRIMARY KEY (`idcustomer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`orderproducts` ;

CREATE TABLE IF NOT EXISTS `mydb`.`orderproducts` (
  `idorder` INT NOT NULL AUTO_INCREMENT,
  `idcustomer` INT NULL,
  `price` INT NULL,
  `payment` TINYINT NULL,
  `delivery` TINYINT NULL,
  `ordercol` VARCHAR(45) NULL,
  PRIMARY KEY (`idorder`),
  INDEX `orderproducts_customer_fk_idx` (`idcustomer` ASC) VISIBLE,
  CONSTRAINT `orderproducts_customer_fk`
    FOREIGN KEY (`idcustomer`)
    REFERENCES `mydb`.`customer` (`idcustomer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`product` ;

CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `idproduct` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `price` INT NULL,
  PRIMARY KEY (`idproduct`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`orderdetail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`orderdetail` ;

CREATE TABLE IF NOT EXISTS `mydb`.`orderdetail` (
  `idorder` INT NOT NULL,
  `idproduct` INT NOT NULL,
  `cnt` INT NULL,
  PRIMARY KEY (`idorder`, `idproduct`),
  INDEX `orderdetail_idproduct_fk_idx` (`idproduct` ASC) VISIBLE,
  CONSTRAINT `orderdetail_idorder_fk`
    FOREIGN KEY (`idorder`)
    REFERENCES `mydb`.`order` (`idorder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `orderdetail_idproduct_fk`
    FOREIGN KEY (`idproduct`)
    REFERENCES `mydb`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


use mydb;

select * from customer;
select * from orderdetail;
select * from product;
select * from orderproducts;
