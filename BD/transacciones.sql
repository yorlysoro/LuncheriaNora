/*
 * transacciones.sql
 * 
 * Copyright 2019 yorlysoro <yorlysoro@gmail.com>
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * 
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above
 *   copyright notice, this list of conditions and the following disclaimer
 *   in the documentation and/or other materials provided with the
 *   distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * 
 */

SET AUTOCOMMIT=0;

START TRANSACTION; /*o BEGIN;*/

LOCK TABLE clientes WRITE;

/*Creando clientes*/

INSERT INTO clientes(cedula, nombres, apellidos, status)
VALUES ('9154005','PEDRO JOSÉ', 'ARROYO ANDRADEZ', 1); 
 
INSERT INTO clientes(cedula, nombres, apellidos, telefono, tipo_telefono, status)
VALUES ('11262635','RAFAEL ENRIQUE', 'VARGAS RIERA', '04265898965', 1, 1); 
 
INSERT INTO clientes(cedula, nombres, apellidos, direccion, telefono, tipo_telefono, status)
VALUES ('17728034','MIGDALIA JOSEFINA', 'SALAZAR PEÑA', 'Nuevo Barrio','02514589685', 2, 1); 
 
UNLOCK TABLES;
/*Agregando productos*/
LOCK TABLE productos WRITE;

INSERT INTO productos(codigo, nombre, Descripcion, Precio, Categorias, Cant, status)
VALUES ('123456789', 'Empanada', 'Empanada rellena con queso', 2000, 1, 80, 1);

INSERT INTO productos(codigo, nombre, Descripcion, Precio, Categorias, Cant, status)
VALUES ('123456789123', 'Jugo Natural', 'Jugo de uva', 1500,  2, 10, 1);

INSERT INTO productos(codigo, nombre, Descripcion, Precio, Categorias, Cant, status)
VALUES ('123456789123456', 'Samba', 'Producto Samba de colombina', 5000, 3, 30, 1);

UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=0;
START TRANSACTION;

/*Creando facturas*/

LOCK TABLES factura WRITE;

INSERT INTO factura(cod_factura, ced_cliente, fecha, status) 
values(1, '9154005', current_date(), 1);

INSERT INTO factura(cod_factura, ced_cliente, fecha, status) 
values(2, '11262635', current_date(), 1);

INSERT INTO factura(cod_factura, ced_cliente, fecha, status) 
values(3, '17728034', current_date(), 1);

UNLOCK TABLES;

COMMIT;
SET AUTOCOMMIT=0;
START TRANSACTION;

LOCK TABLE detalle_factura WRITE;

INSERT INTO detalle_factura(numero_factura, cod_producto, cant, precio_venta) 
VALUES(1, '123456789', 3, 6000);

INSERT INTO detalle_factura(numero_factura, cod_producto, cant, precio_venta) 
VALUES(1, '123456789123', 1, 1500);

INSERT INTO detalle_factura(numero_factura, cod_producto, cant, precio_venta) 
VALUES(2, '123456789123456', 2, 10000);

INSERT INTO detalle_factura(numero_factura, cod_producto, cant, precio_venta) 
VALUES(2, '123456789', 2, 4000);

INSERT INTO detalle_factura(numero_factura, cod_producto, cant, precio_venta) 
VALUES(3, '123456789123', 2, 3000);

INSERT INTO detalle_factura(numero_factura, cod_producto, cant, precio_venta) 
VALUES(3, '123456789123456', 1, 5000);

UNLOCK TABLES;

COMMIT;
SET AUTOCOMMIT=0;
START TRANSACTION;
/*Creando usuarios y empleados*/

LOCK TABLE empleados WRITE;

INSERT INTO empleados(cedula, nombres, apellidos, correo, telefono, tipo_telefono, genero, status)
VALUES ('25142034', 'YORLYS JOSE', 'OROPEZA ESCALONA', 'yorlysoro@gmail.com', '04262011594', 1, 'M', 1);

INSERT INTO empleados(cedula, nombres, apellidos, correo, telefono, tipo_telefono, genero, status)
VALUES ('24157565', 'DAMELYS CORALIS', 'CARRILLO GIMENEZ', 'damelyscarrillo@gmail.com', '04241549685', 1, 'F', 1);

INSERT INTO empleados(cedula, nombres, apellidos, telefono, tipo_telefono, genero, status)
VALUES ('23918948', 'JERRINSON JOSE', 'GARCIA CALZADA',  '04265897485', 1, 'M', 1);

UNLOCK TABLES;

COMMIT;
SET AUTOCOMMIT=0;
START TRANSACTION;
LOCK TABLE usuarios WRITE;

INSERT INTO usuarios(cedula_empleado, nombre_usuario, contrasena, cod_nivel, status)
VALUES('25142034', 'yorlys', 'Yorlys123#', 2, 1);

INSERT INTO usuarios(cedula_empleado, nombre_usuario, contrasena, cod_nivel, status)
VALUES('24157565', 'damelys', 'Damelys123#', 2, 1);

INSERT INTO usuarios(cedula_empleado, nombre_usuario, contrasena, cod_nivel, status)
VALUES('23918948', 'jerry', 'Jerry123#', 3, 1);

UNLOCK TABLES;

COMMIT;


