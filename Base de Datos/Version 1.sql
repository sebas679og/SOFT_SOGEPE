-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SOGEPE
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SOGEPE
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SOGEPE` DEFAULT CHARACTER SET utf8 ;
USE `SOGEPE` ;

-- -----------------------------------------------------
-- Table `SOGEPE`.`Estado_Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Estado_Usuarios` (
  `idEstado_Usuarios` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEstado_Usuarios`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Roles` (
  `idRoles` INT NOT NULL AUTO_INCREMENT,
  `rol` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idRoles`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Generos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Generos` (
  `idGeneros` INT NOT NULL AUTO_INCREMENT,
  `genero` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idGeneros`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Tipo_Documentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Tipo_Documentos` (
  `idTipo_Documentos` INT NOT NULL AUTO_INCREMENT,
  `tipoDocumento` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idTipo_Documentos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Personas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Personas` (
  `idPersonas` INT NOT NULL AUTO_INCREMENT,
  `numeroIdentificacion` INT NOT NULL,
  `primerNombre` VARCHAR(80) NOT NULL,
  `segundoNombre` VARCHAR(80) NULL,
  `primerApellido` VARCHAR(80) NOT NULL,
  `segundoApellido` VARCHAR(80) NOT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  `genero` INT NOT NULL,
  `tipoDocumento` INT NOT NULL,
  PRIMARY KEY (`idPersonas`),
  UNIQUE INDEX `numero_de_documento_UNIQUE` (`numeroIdentificacion` ASC) VISIBLE,
  INDEX `fk_Personas_Generos1_idx` (`genero` ASC) VISIBLE,
  INDEX `fk_Personas_Tipo_Documentos1_idx` (`tipoDocumento` ASC) VISIBLE,
  CONSTRAINT `fk_Personas_Generos1`
    FOREIGN KEY (`genero`)
    REFERENCES `SOGEPE`.`Generos` (`idGeneros`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Personas_Tipo_Documentos1`
    FOREIGN KEY (`tipoDocumento`)
    REFERENCES `SOGEPE`.`Tipo_Documentos` (`idTipo_Documentos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Usuarios` (
  `idUsuarios` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `contraseña` VARCHAR(20) NOT NULL,
  `correo` VARCHAR(150) NOT NULL,
  `estadoUsuario` INT NOT NULL,
  `rol` INT NOT NULL,
  `persona` INT NOT NULL,
  PRIMARY KEY (`idUsuarios`),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  INDEX `fk_Usuarios_Estado_Usuarios1_idx` (`estadoUsuario` ASC) VISIBLE,
  INDEX `fk_Usuarios_Roles1_idx` (`rol` ASC) VISIBLE,
  INDEX `fk_Usuarios_Personas1_idx` (`persona` ASC) VISIBLE,
  CONSTRAINT `fk_Usuarios_Estado_Usuarios1`
    FOREIGN KEY (`estadoUsuario`)
    REFERENCES `SOGEPE`.`Estado_Usuarios` (`idEstado_Usuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuarios_Roles1`
    FOREIGN KEY (`rol`)
    REFERENCES `SOGEPE`.`Roles` (`idRoles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuarios_Personas1`
    FOREIGN KEY (`persona`)
    REFERENCES `SOGEPE`.`Personas` (`idPersonas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Areas_trabajos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Areas_trabajos` (
  `idArea_trabajo` INT NOT NULL AUTO_INCREMENT,
  `area` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`idArea_trabajo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Actividades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Actividades` (
  `idActividades` INT NOT NULL AUTO_INCREMENT,
  `actividad` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idActividades`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Registro_de_actividades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Registro_de_actividades` (
  `idRegistro_de_actividades` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(1000) NOT NULL,
  `observacion` VARCHAR(500) NOT NULL,
  `areaTrabajo` INT NOT NULL,
  `actividad` INT NOT NULL,
  `fechaRegistro` DATETIME NOT NULL,
  `usuario` INT NOT NULL,
  PRIMARY KEY (`idRegistro_de_actividades`),
  INDEX `fk_Registro_de_actividades_Areas_trabajos1_idx` (`areaTrabajo` ASC) VISIBLE,
  INDEX `fk_Registro_de_actividades_Actividades1_idx` (`actividad` ASC) VISIBLE,
  INDEX `fk_Registro_de_actividades_Usuarios1_idx` (`usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Registro_de_actividades_Areas_trabajos1`
    FOREIGN KEY (`areaTrabajo`)
    REFERENCES `SOGEPE`.`Areas_trabajos` (`idArea_trabajo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Registro_de_actividades_Actividades1`
    FOREIGN KEY (`actividad`)
    REFERENCES `SOGEPE`.`Actividades` (`idActividades`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Registro_de_actividades_Usuarios1`
    FOREIGN KEY (`usuario`)
    REFERENCES `SOGEPE`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Estado_objetos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Estado_objetos` (
  `idEstado_objetos` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idEstado_objetos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Composicion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Composicion` (
  `idComposicion` INT NOT NULL AUTO_INCREMENT,
  `composicion` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idComposicion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Productos` (
  `idProductos` INT NOT NULL AUTO_INCREMENT,
  `producto` VARCHAR(100) NOT NULL,
  `valorCompra` FLOAT NOT NULL,
  `valorVenta` FLOAT NOT NULL,
  `estadoDelObjeto` INT NOT NULL,
  `composicion` INT NOT NULL,
  PRIMARY KEY (`idProductos`),
  INDEX `fk_Productos_Estado_objetos1_idx` (`estadoDelObjeto` ASC) VISIBLE,
  INDEX `fk_Productos_Composicion1_idx` (`composicion` ASC) VISIBLE,
  CONSTRAINT `fk_Productos_Estado_objetos1`
    FOREIGN KEY (`estadoDelObjeto`)
    REFERENCES `SOGEPE`.`Estado_objetos` (`idEstado_objetos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Productos_Composicion1`
    FOREIGN KEY (`composicion`)
    REFERENCES `SOGEPE`.`Composicion` (`idComposicion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Inventario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Inventario` (
  `idInventario` INT NOT NULL AUTO_INCREMENT,
  `fechaIngreso` DATETIME NOT NULL,
  `cantidad` INT NOT NULL DEFAULT 1,
  `fechaSalida` DATETIME NOT NULL,
  `producto` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`idInventario`),
  INDEX `fk_Inventario_Productos1_idx` (`producto` ASC) VISIBLE,
  CONSTRAINT `fk_Inventario_Productos1`
    FOREIGN KEY (`producto`)
    REFERENCES `SOGEPE`.`Productos` (`idProductos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Modulos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Modulos` (
  `idModulos` INT NOT NULL AUTO_INCREMENT,
  `modulo` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idModulos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Permisos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Permisos` (
  `idPermisos` INT NOT NULL AUTO_INCREMENT,
  `permiso` VARCHAR(100) NOT NULL,
  `descripcionPermiso` VARCHAR(300) NULL,
  PRIMARY KEY (`idPermisos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Modulos_Roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Modulos_Roles` (
  `idModulos` INT NOT NULL,
  `idRoles` INT NOT NULL,
  PRIMARY KEY (`idModulos`, `idRoles`),
  INDEX `fk_Modulos_has_Roles_Roles1_idx` (`idRoles` ASC) VISIBLE,
  INDEX `fk_Modulos_has_Roles_Modulos1_idx` (`idModulos` ASC) VISIBLE,
  CONSTRAINT `fk_Modulos_has_Roles_Modulos1`
    FOREIGN KEY (`idModulos`)
    REFERENCES `SOGEPE`.`Modulos` (`idModulos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Modulos_has_Roles_Roles1`
    FOREIGN KEY (`idRoles`)
    REFERENCES `SOGEPE`.`Roles` (`idRoles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Roles_Permisos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Roles_Permisos` (
  `idRoles` INT NOT NULL,
  `idPermisos` INT NOT NULL,
  PRIMARY KEY (`idRoles`, `idPermisos`),
  INDEX `fk_Roles_has_Permisos_Permisos1_idx` (`idPermisos` ASC) VISIBLE,
  INDEX `fk_Roles_has_Permisos_Roles1_idx` (`idRoles` ASC) VISIBLE,
  CONSTRAINT `fk_Roles_has_Permisos_Roles1`
    FOREIGN KEY (`idRoles`)
    REFERENCES `SOGEPE`.`Roles` (`idRoles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Roles_has_Permisos_Permisos1`
    FOREIGN KEY (`idPermisos`)
    REFERENCES `SOGEPE`.`Permisos` (`idPermisos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;