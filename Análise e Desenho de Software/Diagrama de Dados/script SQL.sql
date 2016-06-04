SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, 
UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, 
FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, 
SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';



CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

USE `mydb` ;



-- -----------------------------------------------------
-- Table `mydb`.`UF`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mydb`.
`UF` (
  `PK_UF_IDUF` VARCHAR(2) NOT NULL,
  
PRIMARY KEY (`PK_UF_IDUF`))

ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `mydb`.`Cidade`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mydb`.`Cidade` 
(
  `PK_CID_IDCID` VARCHAR(100) NOT NULL,
  
`FK_UF_IDUF` VARCHAR(2) NOT NULL,
  
PRIMARY KEY (`PK_CID_IDCID`),
  
INDEX `fk_Cidade_UF1_idx` (`FK_UF_IDUF` ASC),
  
CONSTRAINT `fk_Cidade_UF1`
    FOREIGN KEY (`FK_UF_IDUF`)
    
REFERENCES `mydb`.`UF` (`PK_UF_IDUF`)
    
ON DELETE NO ACTION
    
ON UPDATE NO ACTION)

ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `mydb`.`Endereco`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mydb`.`Endereco` 
(
  `PK_END_IDEND` INT NOT NULL,
  
`END_DESCRICAO` VARCHAR(100) NOT NULL,
  
`FK_CID_IDCID` INT NOT NULL,
  
PRIMARY KEY (`PK_END_IDEND`),
  
INDEX `fk_Endereco_Cidade1_idx` (`FK_CID_IDCID` ASC),
  
CONSTRAINT `fk_Endereco_Cidade1`
    
FOREIGN KEY (`FK_CID_IDCID`)
    
REFERENCES `mydb`.`Cidade` (`PK_CID_IDCID`)
    
ON DELETE NO ACTION
    
ON UPDATE NO ACTION)

ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `mydb`.`Telefone`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mydb`.`Telefone` 
(
  `PK_TEL_TELID` VARCHAR(11) NOT NULL,
  
PRIMARY KEY (`PK_TEL_TELID`),
  
UNIQUE INDEX `Telefone_UNIQUE` (`PK_TEL_TELID` ASC))

ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `mydb`.`Cliente`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mydb`.`Cliente` 
(
  `PK_CLI_CPF` INT(11) NOT NULL,
  
`CLI_NOME` VARCHAR(50) NOT NULL,
  
`CLI_SEXO` CHAR NOT NULL,
  
`FK_END_IDEND` INT NOT NULL,
  
`FK_CID_IDCID` INT NOT NULL,
  
`FK_TEL_TELID` INT(11) NOT NULL,
  
PRIMARY KEY (`PK_CLI_CPF`),
  
INDEX `fk_Cliente_Endereco1_idx` 
(`FK_END_IDEND` ASC, 
`FK_CID_IDCID` ASC),
  
INDEX `fk_Cliente_Telefone1_idx` (`FK_TEL_TELID` ASC),
  
CONSTRAINT `fk_Cliente_Endereco1`
    
FOREIGN KEY (`FK_END_IDEND` , `FK_CID_IDCID`)
    
REFERENCES `mydb`.`Endereco` (`PK_END_IDEND` , `FK_CID_IDCID`)
    
ON DELETE NO ACTION
    
ON UPDATE NO ACTION,
  
CONSTRAINT `fk_Cliente_Telefone1`
    
FOREIGN KEY (`FK_TEL_TELID`)
    
REFERENCES `mydb`.`Telefone` (`PK_TEL_TELID`)
    
ON DELETE NO ACTION
    
ON UPDATE NO ACTION)

ENGINE = InnoDB;






-- -----------------------------------------------------
-- Table `mydb`.`Empresa`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mydb`.`Empresa` 
(
  `PK_EMP_CNPJ` INT(14) NOT NULL,
  
`EMP_NOME_FANTASIA` VARCHAR(100) NOT NULL,
  
`EMP_RAZAO_SOCIAL` VARCHAR(100) NOT NULL,
  
`FK_END_IDEND` INT NOT NULL,
  
`FK_CID_IDCID` VARCHAR(100) NOT NULL,
  
`FK_PRO_CPF` INT NOT NULL,
  
`FK_TEL_TELID` VARCHAR(11) NOT NULL,
  
PRIMARY KEY (`PK_EMP_CNPJ`),
  
INDEX `fk_Empresa_Endereco1_idx` (`FK_END_IDEND` ASC, `FK_CID_IDCID` ASC),
  
INDEX `fk_Empresa_Profissional1_idx` (`FK_PRO_CPF` ASC),
  
INDEX `fk_Empresa_Telefone1_idx` (`FK_TEL_TELID` ASC),
  
CONSTRAINT `fk_Empresa_Endereco1`
    
FOREIGN KEY (`FK_END_IDEND` , `FK_CID_IDCID`)
    
REFERENCES `mydb`.`Endereco` (`PK_END_IDEND` , `FK_CID_IDCID`)
    
ON DELETE NO ACTION
    
ON UPDATE NO ACTION,
  
CONSTRAINT `fk_Empresa_Profissional1`
    
FOREIGN KEY (`FK_PRO_CPF`)
    
REFERENCES `mydb`.`Profissional` (`PK_PRO_CPF`)
    
ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  
CONSTRAINT `fk_Empresa_Telefone1`
    
FOREIGN KEY (`FK_TEL_TELID`)
    
REFERENCES `mydb`.`Telefone` (`PK_TEL_TELID`)
    
ON DELETE NO ACTION
    
ON UPDATE NO ACTION)

ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `mydb`.`tipo_servico`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mydb`.`tipo_servico` 
(
  `PK_TSE_IDTSE` INT NOT NULL AUTO_INCREMENT,
  
`TSE_DESCRICAO` VARCHAR(50) NOT NULL,
  
PRIMARY KEY (`PK_TSE_IDTSE`))

ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `mydb`.`Servicos`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `mydb`.`Servicos` 
(
  `PK_SER_IDSER` INT NOT NULL AUTO_INCREMENT,
  
`FK_TSE_IDTSE` INT NOT NULL,
  
`SER_DESCRICAO` VARCHAR(50) NOT NULL,
  
`SER_DURACAO` INT NOT NULL,
  
`SER_VALOR` DOUBLE NOT NULL,
 
PRIMARY KEY (`PK_SER_IDSER`),
  
INDEX `fk_Servicos_tipo_servico_idx` (`FK_TSE_IDTSE` ASC),
  
CONSTRAINT `fk_Servicos_tipo_servico`
    
FOREIGN KEY (`FK_TSE_IDTSE`)
    
REFERENCES `mydb`.`tipo_servico` (`PK_TSE_IDTSE`)
    
ON DELETE NO ACTION
    
ON UPDATE NO ACTION)

ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Agenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Agenda` 
(
  `PK_AGE_IDAGE` INT NOT NULL AUTO_INCREMENT,
  
`AGE_EMP_NOME` INT NOT NULL,
  
`AGE_PRO_NOME` VARCHAR(100) NOT NULL,
  
`AGE_CLI_NOME` VARCHAR(100) NOT NULL,
  
`AGE_DATA` DATE NOT NULL,
  
`AGE_HORARIO` TIME NOT NULL,
  
`FK_CLI_CPF` INT(11) NOT NULL,
  
`FK_EMP_CNPJ` INT(14) NOT NULL,
  
`FK_PRO_CPF` INT NOT NULL,
  
`FK_SER_IDSER` INT NOT NULL,
  
PRIMARY KEY (`PK_AGE_IDAGE`),
  
INDEX `fk_Agenda_Cliente1_idx` (`FK_CLI_CPF` ASC),
  
INDEX `fk_Agenda_Empresa1_idx` (`FK_EMP_CNPJ` ASC),
  
INDEX `fk_Agenda_Profissional1_idx` (`FK_PRO_CPF` ASC),
  
INDEX `fk_Agenda_Servicos1_idx` (`FK_SER_IDSER` ASC),
  
CONSTRAINT `fk_Agenda_Cliente1`
    FOREIGN KEY (`FK_CLI_CPF`)
    
REFERENCES `mydb`.`Cliente` (`PK_CLI_CPF`)
    
ON DELETE NO ACTION
    
ON UPDATE NO ACTION,
  
CONSTRAINT `fk_Agenda_Empresa1`
    
FOREIGN KEY (`FK_EMP_CNPJ`)
    
REFERENCES `mydb`.`Empresa` (`PK_EMP_CNPJ`)
    
ON DELETE NO ACTION
    
ON UPDATE NO ACTION,
  
CONSTRAINT `fk_Agenda_Profissional1`
    
FOREIGN KEY (`FK_PRO_CPF`)
    
REFERENCES `mydb`.`Profissional` (`PK_PRO_CPF`)
    
ON DELETE NO ACTION
    
ON UPDATE NO ACTION,
  
CONSTRAINT `fk_Agenda_Servicos1`
    
FOREIGN KEY (`FK_SER_IDSER`)
    
REFERENCES `mydb`.`Servicos` (`PK_SER_IDSER`)
    
ON DELETE NO ACTION
    
ON UPDATE NO ACTION)

ENGINE = InnoDB;




SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
