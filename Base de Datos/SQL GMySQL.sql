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
-- -----------------------------------------------------
-- Schema sogepe
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sogepe
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sogepe` DEFAULT CHARACTER SET utf8mb3 ;
USE `SOGEPE` ;

-- -----------------------------------------------------
-- Table `SOGEPE`.`Estado_Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Estado_Usuarios` (
  `idEstado` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEstado`))
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
  `idDocumento` INT NOT NULL AUTO_INCREMENT,
  `tipoDocumento` VARCHAR(100) NOT NULL,
  `sigla` VARCHAR(45) NULL,
  PRIMARY KEY (`idDocumento`))
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
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Personas_Tipo_Documentos1`
    FOREIGN KEY (`tipoDocumento`)
    REFERENCES `SOGEPE`.`Tipo_Documentos` (`idDocumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Usuarios` (
  `idUsuarios` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `contraseña` VARCHAR(110) NOT NULL,
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
    REFERENCES `SOGEPE`.`Estado_Usuarios` (`idEstado`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Usuarios_Roles1`
    FOREIGN KEY (`rol`)
    REFERENCES `SOGEPE`.`Roles` (`idRoles`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Usuarios_Personas1`
    FOREIGN KEY (`persona`)
    REFERENCES `SOGEPE`.`Personas` (`idPersonas`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Areas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Areas` (
  `idArea` INT NOT NULL AUTO_INCREMENT,
  `area` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`idArea`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Actividades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Actividades` (
  `idActividades` INT NOT NULL AUTO_INCREMENT,
  `actividad` VARCHAR(200) NOT NULL,
  `area` INT NOT NULL,
  PRIMARY KEY (`idActividades`),
  INDEX `fk_Actividades_Areas1_idx` (`area` ASC) VISIBLE,
  CONSTRAINT `fk_Actividades_Areas1`
    FOREIGN KEY (`area`)
    REFERENCES `SOGEPE`.`Areas` (`idArea`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Registro_Informes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Registro_Informes` (
  `idRegistro` INT NOT NULL AUTO_INCREMENT,
  `usuario` INT NOT NULL,
  `areaTrabajo` INT NOT NULL,
  `actividad` INT NOT NULL,
  `fechaRegistro` DATETIME NOT NULL,
  `descripcion` TEXT NOT NULL,
  `observacion` TEXT NOT NULL,
  PRIMARY KEY (`idRegistro`),
  INDEX `fk_Registro_de_actividades_Areas_trabajos1_idx` (`areaTrabajo` ASC) VISIBLE,
  INDEX `fk_Registro_de_actividades_Actividades1_idx` (`actividad` ASC) VISIBLE,
  INDEX `fk_Registro_de_actividades_Usuarios1_idx` (`usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Registro_de_actividades_Areas_trabajos1`
    FOREIGN KEY (`areaTrabajo`)
    REFERENCES `SOGEPE`.`Areas` (`idArea`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Registro_de_actividades_Actividades1`
    FOREIGN KEY (`actividad`)
    REFERENCES `SOGEPE`.`Actividades` (`idActividades`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Registro_de_actividades_Usuarios1`
    FOREIGN KEY (`usuario`)
    REFERENCES `SOGEPE`.`Usuarios` (`idUsuarios`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`Categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`Categorias` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `categoria` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`productos` (
  `idProductos` INT NOT NULL AUTO_INCREMENT,
  `codigo` INT NOT NULL,
  `producto` VARCHAR(100) NOT NULL,
  `categoria` INT NOT NULL,
  PRIMARY KEY (`idProductos`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE,
  INDEX `fk_productos_Categoria_producto1_idx` (`categoria` ASC) VISIBLE,
  CONSTRAINT `fk_productos_Categoria_producto1`
    FOREIGN KEY (`categoria`)
    REFERENCES `SOGEPE`.`Categorias` (`idCategoria`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SOGEPE`.`inventario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOGEPE`.`inventario` (
  `idInventario` INT NOT NULL AUTO_INCREMENT,
  `fechaIngreso` DATETIME NOT NULL,
  `cantidad` INT NOT NULL,
  `producto` INT NOT NULL,
  PRIMARY KEY (`idInventario`),
  INDEX `fk_inventario_productos1_idx` (`producto` ASC) VISIBLE,
  CONSTRAINT `fk_inventario_productos1`
    FOREIGN KEY (`producto`)
    REFERENCES `SOGEPE`.`productos` (`idProductos`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `sogepe` ;

-- -----------------------------------------------------
-- Table `sogepe`.`actividades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`actividades` (
  `idActividades` INT NOT NULL AUTO_INCREMENT,
  `actividad` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idActividades`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`areas_trabajos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`areas_trabajos` (
  `idArea_trabajo` INT NOT NULL AUTO_INCREMENT,
  `area` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`idArea_trabajo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`categoria_producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`categoria_producto` (
  `idCategoria` INT NOT NULL AUTO_INCREMENT,
  `categoria` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`estado_usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`estado_usuarios` (
  `idEstado_Usuarios` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEstado_Usuarios`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`generos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`generos` (
  `idGeneros` INT NOT NULL AUTO_INCREMENT,
  `genero` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idGeneros`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`productos` (
  `idProductos` INT NOT NULL AUTO_INCREMENT,
  `producto` VARCHAR(100) NOT NULL,
  `valorCompra` FLOAT NOT NULL,
  `categoria` INT NOT NULL,
  PRIMARY KEY (`idProductos`),
  INDEX `ctegoria_idx` (`categoria` ASC) VISIBLE,
  CONSTRAINT `fk_categoria`
    FOREIGN KEY (`categoria`)
    REFERENCES `sogepe`.`categoria_producto` (`idCategoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`inventario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`inventario` (
  `idInventario` INT NOT NULL AUTO_INCREMENT,
  `fechaIngreso` DATETIME NOT NULL,
  `cantidad` INT NOT NULL DEFAULT '1',
  `producto` INT NOT NULL,
  PRIMARY KEY (`idInventario`),
  INDEX `fk_Inventario_Productos1_idx` (`producto` ASC) VISIBLE,
  CONSTRAINT `fk_Inventario_Productos1`
    FOREIGN KEY (`producto`)
    REFERENCES `sogepe`.`productos` (`idProductos`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`modulos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`modulos` (
  `idModulos` INT NOT NULL AUTO_INCREMENT,
  `modulo` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idModulos`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`roles` (
  `idRoles` INT NOT NULL AUTO_INCREMENT,
  `rol` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idRoles`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`modulos_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`modulos_roles` (
  `idModulos` INT NOT NULL,
  `idRoles` INT NOT NULL,
  PRIMARY KEY (`idModulos`, `idRoles`),
  INDEX `fk_Modulos_has_Roles_Roles1_idx` (`idRoles` ASC) VISIBLE,
  INDEX `fk_Modulos_has_Roles_Modulos1_idx` (`idModulos` ASC) VISIBLE,
  CONSTRAINT `fk_Modulos_has_Roles_Modulos1`
    FOREIGN KEY (`idModulos`)
    REFERENCES `sogepe`.`modulos` (`idModulos`),
  CONSTRAINT `fk_Modulos_has_Roles_Roles1`
    FOREIGN KEY (`idRoles`)
    REFERENCES `sogepe`.`roles` (`idRoles`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`permisos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`permisos` (
  `idPermisos` INT NOT NULL AUTO_INCREMENT,
  `permiso` VARCHAR(100) NOT NULL,
  `descripcionPermiso` VARCHAR(300) NULL DEFAULT NULL,
  PRIMARY KEY (`idPermisos`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`tipo_documentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`tipo_documentos` (
  `idTipo_Documentos` INT NOT NULL AUTO_INCREMENT,
  `tipoDocumento` VARCHAR(100) NOT NULL,
  `sigla` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`idTipo_Documentos`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`personas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`personas` (
  `idPersonas` INT NOT NULL AUTO_INCREMENT,
  `tipoDocumento` INT NOT NULL,
  `numeroIdentificacion` INT NOT NULL,
  `primerNombre` VARCHAR(80) NOT NULL,
  `segundoNombre` VARCHAR(80) NULL DEFAULT NULL,
  `primerApellido` VARCHAR(80) NOT NULL,
  `segundoApellido` VARCHAR(80) NOT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  `genero` INT NOT NULL,
  PRIMARY KEY (`idPersonas`),
  UNIQUE INDEX `numero_de_documento_UNIQUE` (`numeroIdentificacion` ASC) VISIBLE,
  INDEX `fk_Personas_Generos1_idx` (`genero` ASC) VISIBLE,
  INDEX `fk_Personas_Tipo_Documentos1_idx` (`tipoDocumento` ASC) VISIBLE,
  CONSTRAINT `fk_Personas_Generos1`
    FOREIGN KEY (`genero`)
    REFERENCES `sogepe`.`generos` (`idGeneros`),
  CONSTRAINT `fk_Personas_Tipo_Documentos1`
    FOREIGN KEY (`tipoDocumento`)
    REFERENCES `sogepe`.`tipo_documentos` (`idTipo_Documentos`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`usuarios` (
  `idUsuarios` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `contraseña` VARCHAR(255) NOT NULL,
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
    REFERENCES `sogepe`.`estado_usuarios` (`idEstado_Usuarios`),
  CONSTRAINT `fk_Usuarios_Personas1`
    FOREIGN KEY (`persona`)
    REFERENCES `sogepe`.`personas` (`idPersonas`),
  CONSTRAINT `fk_Usuarios_Roles1`
    FOREIGN KEY (`rol`)
    REFERENCES `sogepe`.`roles` (`idRoles`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`registro_de_actividades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`registro_de_actividades` (
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
  CONSTRAINT `fk_Registro_de_actividades_Actividades1`
    FOREIGN KEY (`actividad`)
    REFERENCES `sogepe`.`actividades` (`idActividades`),
  CONSTRAINT `fk_Registro_de_actividades_Areas_trabajos1`
    FOREIGN KEY (`areaTrabajo`)
    REFERENCES `sogepe`.`areas_trabajos` (`idArea_trabajo`),
  CONSTRAINT `fk_Registro_de_actividades_Usuarios1`
    FOREIGN KEY (`usuario`)
    REFERENCES `sogepe`.`usuarios` (`idUsuarios`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `sogepe`.`roles_permisos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sogepe`.`roles_permisos` (
  `idRoles` INT NOT NULL,
  `idPermisos` INT NOT NULL,
  PRIMARY KEY (`idRoles`, `idPermisos`),
  INDEX `fk_Roles_has_Permisos_Permisos1_idx` (`idPermisos` ASC) VISIBLE,
  INDEX `fk_Roles_has_Permisos_Roles1_idx` (`idRoles` ASC) VISIBLE,
  CONSTRAINT `fk_Roles_has_Permisos_Permisos1`
    FOREIGN KEY (`idPermisos`)
    REFERENCES `sogepe`.`permisos` (`idPermisos`),
  CONSTRAINT `fk_Roles_has_Permisos_Roles1`
    FOREIGN KEY (`idRoles`)
    REFERENCES `sogepe`.`roles` (`idRoles`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
