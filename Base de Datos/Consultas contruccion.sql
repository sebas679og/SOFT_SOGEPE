use sogepe;

select * from usuarios;
describe personas;
describe usuarios;

insert into personas (tipoDocumento, numeroIdentificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, telefono, direccion, genero)
	values (1, 1234567809, 'LILIT', '', 'MOLANO', 'CIFUENTES', '3124567890', 'CR4 7-39', 1);
    
insert into usuarios (username, contraseña, correo, estadoUsuario, rol, persona)
	values ('lilitfuentes', 'lilit123', 'lilit@dominio.com', 1, 2, 2);
    
select * from usuarios;
describe personas;


ALTER TABLE usuarios MODIFY contraseña VARCHAR(255) NOT NULL;

use sogepe;

SELECT td.sigla AS tipo_documentos, p.numeroIdentificacion, r.rol AS roles, eu.estado AS estadoUsuario
FROM personas p 
JOIN usuarios u ON p.idPersonas = u.persona 
JOIN roles r ON u.rol = r.idRoles 
JOIN tipo_documentos td ON p.tipoDocumento = td.idTipo_Documentos 
JOIN estado_usuarios eu ON u.estadoUsuario = eu.idEstado_Usuarios;

SELECT u.username AS Username, u.contraseña AS Contraseña, r.rol AS Rol
FROM usuarios u
JOIN roles r ON u.rol = r.idRoles;

SELECT u.username, u.contraseña, u.correo, u.estadoUsuario, u.rol, p.tipoDocumento, p.numeroIdentificacion, p.primerNombre, p.segundoNombre, p.primerApellido, p.segundoApellido, p.telefono, p.direccion, p.genero
FROM usuarios u 
JOIN personas p ON u.persona = p.idPersonas;	

SELECT u.username, u.contraseña, u.correo, u.estadoUsuario, u.rol, p.tipoDocumento, p.numeroIdentificacion, p.primerNombre, p.segundoNombre, p.primerApellido, p.segundoApellido, p.telefono, p.direccion, p.genero, p.idPersonas, u.idUsuarios
FROM usuarios u 
JOIN personas p ON u.persona = p.idPersonas
WHERE u.idUsuarios = 8;

SELECT u.idUsuarios AS id_usuarios, 
       td.sigla AS tipo_documentos, 
       p.numeroIdentificacion, 
       r.rol AS roles, 
       p.idPersonas AS id_personas,
       eu.estado AS estadoUsuario
FROM personas p 
JOIN usuarios u ON p.idPersonas = u.persona 
JOIN roles r ON u.rol = r.idRoles 
JOIN tipo_documentos td ON p.tipoDocumento = td.idDocumento 
JOIN estado_usuarios eu ON u.estadoUsuario = eu.idEstado;

UPDATE usuarios SET username = 'manicur', contraseña = 'Manuela234', correo = 'manuela@dominio.com', estadoUsuario = 1, rol = 4 WHERE idUsuarios = 8;

UPDATE personas SET tipoDocumento = 3, numeroIdentificacion = 1060040990, primerNombre = 'SEBASTIAN', segundoNombre = 'SMITH', primerApellido = 'ORJUELA', 
	segundoApellido = 'GIRALDO', telefono = '3127235001', direccion = 'LA RICA', genero = 2 WHERE idPersonas = 1;

SELECT * FROM personas WHERE idPersonas = 1;
SELECT * FROM usuarios WHERE idUsuarios = 1;
select * FROM personas;
select * FROM usuarios;

INSERT INTO categorias (categoria) VALUES ('PRODUCCION'), ('BODEGA'), ('UTILERIA'), ('MATERIA PRIMA');

SELECT * FROM categorias;

SELECT * FROM productos;

SELECT * FROM productos;

SET SQL_SAFE_UPDATES = 0;

TRUNCATE TABLE productos;

SELECT idDocumento, tipoDocumento FROM tipo_documentos;

SELECT * FROM inventario;

SELECT i.idInventario AS id_inventario, 
	i.fechaIngreso AS fecha_ingreso, 
	c.categoria AS categoria, 
    p.producto AS producto, 
    i.cantidad AS cantidad 
FROM inventario i 
JOIN productos p ON i.producto = p.idProductos 
JOIN Categorias c ON p.categoria = c.idCategoria;

SELECT i.idInventario AS id_inventario, 
	i.fechaIngreso AS fecha_ingreso, 
	c.categoria AS categoria, 
    p.producto AS producto, 
    i.cantidad AS cantidad 
FROM inventario i 
JOIN productos p ON i.producto = p.idProductos 
JOIN Categorias c ON p.categoria = c.idCategoria
WHERE i.idInventario = 7;

SELECT p.codigo AS codigo, p.producto AS producto, c.categoria AS categoria 
FROM productos p 
JOIN categorias c ON p.categoria = c.idCategoria;

SELECT p.codigo AS codigo, p.producto AS producto, c.categoria AS categoria 
FROM productos p 
JOIN categorias c ON p.categoria = c.idCategoria
WHERE p.codigo = 1;

DELETE FROM productos WHERE codigo = 12;

DELETE FROM inventario WHERE idInventario = 1;

SELECT i.idInventario AS id_inventario, 
	i.fechaIngreso AS fecha_ingreso, 
    p.producto AS producto, 
    i.cantidad AS cantidad 
FROM inventario i 
JOIN productos p ON i.producto = p.idProductos 
WHERE i.idInventario = 7;

select * from inventario;
UPDATE inventario SET fechaIngreso = '2024-09-12 21:20:00', cantidad = 123, producto = 3 WHERE idInventario = 6;

select * from registro_informes;

SELECT r.idRegistro AS id_registro, 
       u.username AS username, 
       r.fechaRegistro AS fecha_registro, 
       a.area AS area_trabajo
FROM Registro_Informes r
JOIN Usuarios u ON u.idUsuarios = r.usuario  
JOIN Areas a ON a.idArea = r.areaTrabajo;  

SELECT r.idRegistro AS codigo, 
       u.username AS username, 
       r.fechaRegistro AS fechaRegistro, 
       a.area AS area,
       ac.actividad as actividad,
       r.descripcion AS descripcion, 
       r.observacion AS observacion
FROM Registro_Informes r
JOIN Usuarios u ON u.idUsuarios = r.usuario  
JOIN Areas a ON a.idArea = r.areaTrabajo
JOIN actividades ac ON ac.idActividades = r.actividad
WHERE r.idRegistro = 1;

SELECT i.idInventario AS id_inventario, 
	i.fechaIngreso AS fecha_ingreso, 
    p.producto AS producto, 
    i.cantidad AS cantidad 
FROM inventario i
JOIN productos p ON i.producto = p.idProductos;
                             
