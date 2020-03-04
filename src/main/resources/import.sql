INSERT INTO usuarios (apellido, cedula_pasaport, celular, no_licencia, nombre, telefono) VALUES('Guzmán', '9-87-1246', '65555599', 'profesor@bolsadeideas.com', 'Andrés', '244-5569');
INSERT INTO usuarios (apellido, cedula_pasaport, celular, no_licencia, nombre, telefono) VALUES('Doe', '8-87-1244', '69886655', 'john.doe@gmail.com', 'Mr. John', '244-5569');
/* Creamos algunos carros */
INSERT INTO carros (anio, marca, modelo, precio, precio_x_dia, rentado) VALUES('2019', 'kia', 'rio', 14500.00, 24.00, true);
INSERT INTO carros (anio, marca, modelo, precio, precio_x_dia, rentado) VALUES('2019', 'kia', 'cerato', 17800.00, 27.00, true);
INSERT INTO carros (anio, marca, modelo, precio, precio_x_dia, rentado) VALUES('2018', 'kia', 'sportage', 19000.00, 65.00, true);
INSERT INTO carros (anio, marca, modelo, precio, precio_x_dia, rentado) VALUES('2018', 'kia', 'optima', 19500, 60.00, true);
INSERT INTO carros (anio, marca, modelo, precio, precio_x_dia, rentado) VALUES('2019', 'hiunday', 'Tucson', 18200, 60.00, true);
INSERT INTO carros (anio, marca, modelo, precio, precio_x_dia, rentado) VALUES('2019', 'hiunday', 'Santa Fe', 22200, 67.00, true);
INSERT INTO carros (anio, marca, modelo, precio, precio_x_dia, rentado) VALUES('2019', 'hiunday', 'Elantra', 15200, 60.00, true);
INSERT INTO carros (anio, marca, modelo, precio, precio_x_dia, rentado) VALUES('2018', 'kia', 'picanto', 12000, 20.00, true);
INSERT INTO carros (anio, marca, modelo, precio, precio_x_dia, rentado) VALUES('2018', 'Toyota', 'Corola', 16100, 28.00, true);
INSERT INTO carros (anio, marca, modelo, precio, precio_x_dia, rentado) VALUES('2018', 'Toyota', 'Yaris', 14500, 24.00, true);
INSERT INTO carros (anio, marca, modelo, precio, precio_x_dia, rentado) VALUES('2018', 'Toyota', 'Hilux', 22500, 60.00, true);

/* Creamos algunos usuarios con sus roles */
INSERT INTO `users_login` (username, password, enabled, nombre, apellido, email,id_usuario) VALUES ('andres','$2a$10$YWAxq.XYXCAPVBk9UZ9AGuXVN9ANHwmmTC75hoaOY.2lEmxQa29dq',1, 'Andrés', 'Guzmán', 'profesor@bolsadeideas.com',1);
INSERT INTO `users_login` (username, password, enabled, nombre, apellido, email,id_usuario) VALUES ('admin','$2a$10$YWAxq.XYXCAPVBk9UZ9AGuXVN9ANHwmmTC75hoaOY.2lEmxQa29dq',1 , 'Mr. John', 'Doe', 'john.doe@gmail.com',2);

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);
/* -- ejecutar sp
USE `flash_renta_car`;
DROP procedure IF EXISTS `crear_orden`;

DELIMITER $$
create or replace procedure `crear_orden`(in i_id_usuario int,in i_id_orden int  )
BEGIN

  INSERT INTO usuarios_ordenes (usuario_id, orden_id) VALUES(i_id_usuario, i_id_orden);
END$$
*/