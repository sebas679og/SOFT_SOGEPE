Use sogepe;

describe roles;
insert into `roles` (`rol`) values
("Administrador"), 
("Gerente"), 
("Funcionario de producción"),
("Funcionario de Entrega"),
("Funcionario de Bodega");
select * from roles;

describe generos;
insert into `generos` (genero) values
("Femenino"), ("Masculino");
select * from generos;

describe tipo_documentos;
insert into `tipo_documentos` (tipoDocumento, sigla) values
("Cedula de Ciudadania", "CC"), ("Cedula de Extranjeria", "CE"), ("Permiso Especial de Permanencia", "PEP"),
("Pasaporte", "PAS"), ("Numero de Identificacion de Extranjeros", "NIE");
select * from tipo_documentos;

describe estado_usuarios;
insert into `estado_usuarios` (estado) values 
("Activo"), ("Inactivo"), ("Suspendido");
select * from estado_usuarios;

describe personas;
insert into `personas` (numeroIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, telefono, direccion, genero, tipoDocumento)
 values (1234567890, "SEBASTIAN", "", "ORJUELA", "GIRALDO", "3117031982", "SAMANA-CALDAS", 2, 1);
select * from personas;

select * from roles;
select * from estado_usuarios;
describe usuarios;
insert into `usuarios` (username, contraseña, correo, estadoUsuario, rol, persona)
	values ("admin", "admin", "sebastianorjuelagiraldo@gmail.com", 1, 1, 1);
select * from usuarios;

INSERT INTO categorias (categoria) VALUES ('PRODUCCION'), ('BODEGA'), ('UTILERIA'), ('MATERIA PRIMA');
select * FROM categorias;