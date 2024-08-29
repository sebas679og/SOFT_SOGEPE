Use sogepe;

describe Permisos;
INSERT INTO `Permisos` (`permiso`, `descripcionPermiso`) VALUES
('Crear', 'Permite crear nuevos registros en el módulo.'),
('Modificar', 'Permite actualizar registros existentes en el módulo.'),
('Eliminar', 'Permite eliminar registros del módulo.'),
('Ver', 'Permite visualizar registros en el módulo.'),
('Gestionar Estado', 'Permite cambiar el estado de los registros en el módulo.'),
('Ver Detalle', 'Permite visualizar el detalle de los registros.'),
('Asignar Permisos', 'Permite asignar o modificar permisos dentro del módulo.'),
('Generar Reportes', 'Permite generar y visualizar reportes específicos del módulo.'),
('Acceso Total', 'Permite acceso a todas las funcionalidades del módulo.');
select * from permisos;

describe modulos;
INSERT INTO `Modulos` (`modulo`) VALUES
('Módulo de Gestión de Usuarios'),
('Módulo de Gestión de Inventario'),
('Módulo de Gestión de Actividades'),
('Módulo de Administración General');
select * from modulos;

describe roles;
insert into `roles` (`rol`) values
("Administrador"), 
("Gerente"), 
("Cliente"),
("Funcionario de producción"),
("Funcionario de Entrega"),
("Funcionario de Bodega");
select * from roles;

describe modulos_roles;
insert into `modulos_roles` (idRoles, idModulos) values
(1, 4), (2, 1), (2, 2), (2, 3), (3, 1), (4, 1), (4, 3),
(5, 1), (5, 3), (6, 1), (6, 2), (6, 3);
select * from modulos_roles;

describe roles_permisos;
insert into `roles_permisos` (idRoles, idPermisos) values
(1, 9), (2, 9), (3, 1), (3, 4), (3, 6), (4, 1), (4, 4),
(4, 6), (4, 8), (5, 1), (5, 4), (5, 6), (5, 8), (6, 1),
(6, 2), (6, 3), (6, 4), (6, 5), (6, 6);
select * from roles_permisos;

describe generos;
insert into `generos` (genero) values
("Femenino"), ("Masculino");
select * from generos;

describe tipo_documentos;
insert into `tipo_documentos` (tipoDocumento, sigla) values
("Cedula de Ciudadania", "CC"), ("Cedula de Extranjeria", "CE"), ("Permiso Especial de Permanencia", "PEP"),
("Pasaporte", ""), ("Numero de Identificacion de Extranjeros", "NIE");
select * from tipo_documentos;
SET SQL_SAFE_UPDATES = 0;
UPDATE `tipo_documentos`
SET `sigla` = 'PAS'
WHERE `idTipo_Documentos` = 4;
select * from tipo_documentos;
SET SQL_SAFE_UPDATES = 1;

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
	values ("sebasorjuelag", "sebas1234", "sebastianorjuelagiraldo@gmail.com", 1, 1, 1);
select * from usuarios;