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
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `iduser` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`book` ;

CREATE TABLE IF NOT EXISTS `mydb`.`book` (
  `idbook` INT NOT NULL,
  `writer` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `price` INT,
  `desc` VARCHAR(45) NULL,
  `img` VARCHAR(45) NULL,
  PRIMARY KEY (`idbook`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`satisfaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`satisfaction` ;

CREATE TABLE IF NOT EXISTS `mydb`.`satisfaction` (
  `idbook` INT NOT NULL,
  `iduser` VARCHAR(45) NOT NULL,
  `rating` INT NULL,
  `comment` VARCHAR(45) NULL,
  PRIMARY KEY (`idbook`, `iduser`),
  INDEX `satisfaction_iduser_fk_idx` (`iduser` ASC) VISIBLE,
  CONSTRAINT `satisfaction_idbook_fk`
    FOREIGN KEY (`idbook`)
    REFERENCES `mydb`.`book` (`idbook`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `satisfaction_iduser_fk`
    FOREIGN KEY (`iduser`)
    REFERENCES `mydb`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`recommendation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`recommendation` ;

CREATE TABLE IF NOT EXISTS `mydb`.`recommendation` (
  `iduser` VARCHAR(45) NOT NULL,
  `recom_iduser` VARCHAR(45) NULL,
  PRIMARY KEY (`iduser`),
  INDEX `recommendation_recomIduser_fk_idx` (`recom_iduser` ASC) VISIBLE,
  CONSTRAINT `recommendation_iduser_fk`
    FOREIGN KEY (`iduser`)
    REFERENCES `mydb`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `recommendation_recom_iduser_fk`
    FOREIGN KEY (`recom_iduser`)
    REFERENCES `mydb`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

use mydb;
select * from user;
select * from recommendation;
select * from book;
select * from satisfaction;

insert into user values ("hong", "홍길동", 1234);
insert into user values ("jang", "장길산", 2345);
insert into user values ("lim", "임꺽정", 3456);

insert into recommendation values("hong","jang");
insert into recommendation values("lim","jang");

insert into book values(111,"조용준","자바프로그래밍",25000,"자바 기본","java.jpg");
insert into book values(222,"사무국","즐거운 싸피생활",0,"싸피 생활 안내","ssafy.jpg");

insert into satisfaction values(111,"hong",10,"좋은책");
insert into satisfaction values(111,"jang",8,"도움됨");
insert into satisfaction values(111,"lim",9,"재밌다");
insert into satisfaction values(222,"hong",10,"강추");
insert into satisfaction values(222,"lim",8,"핵재미");

select * 
from satisfaction left join book
on satisfaction.idbook = book.idbook
left join user
on satisfaction.iduser = user.iduser
left join recommendation
on recommendation.iduser = user.iduser;
