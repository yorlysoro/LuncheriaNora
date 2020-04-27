-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-07-2019 a las 18:49:33
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `luncheria`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `Error` (IN `mensaje` TEXT)  BEGIN
	signal sqlstate '23000' set
    mysql_errno = 1452,
	message_text = mensaje;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `acciones`
--

CREATE TABLE `acciones` (
  `cod` int(11) NOT NULL,
  `nombre` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `acciones`
--

INSERT INTO `acciones` (`cod`, `nombre`) VALUES
(1, 'ERROR'),
(2, 'ADVERTENCIA'),
(3, 'INFORMACION'),
(4, 'CONFIGURACION'),
(5, 'MULTA'),
(6, 'MEJOR'),
(7, 'BUENO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad_usuarios`
--

CREATE TABLE `actividad_usuarios` (
  `cod` int(11) NOT NULL,
  `cod_usuario` varchar(8) NOT NULL,
  `Accion` int(11) NOT NULL,
  `Fecha_Hora` datetime NOT NULL,
  `ventana` varchar(64) NOT NULL,
  `mensaje` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `cod` int(11) NOT NULL,
  `nombre` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`cod`, `nombre`) VALUES
(1, 'Comida'),
(2, 'Bebidas'),
(3, 'Golosinas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `cedula` varchar(8) NOT NULL,
  `nombres` varchar(32) NOT NULL,
  `apellidos` varchar(32) NOT NULL,
  `direccion` text,
  `correo` varchar(100) DEFAULT NULL,
  `telefono` varchar(11) DEFAULT NULL,
  `tipo_telefono` int(11) DEFAULT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`cedula`, `nombres`, `apellidos`, `direccion`, `correo`, `telefono`, `tipo_telefono`, `status`) VALUES
('11262635', 'RAFAEL ENRIQUE', 'VARGAS RIERA', 'carora', 'jerrinsonxd@gmail.com', '04265898965', 1, 1),
('123456', 'yorlys', 'oropeza', 'new york', 'yorlysoro@gmail.com', '04261234567', 1, 1),
('17728034', 'MIGDALIA JOSEFINA', 'SALAZAR PEÑA', 'Nuevo Barrio', NULL, '02514589685', 2, 1),
('25142034', 'yorlys', 'oropeza', 'carora edo lara', 'yorlysoro@gmail.com', '04262011594', 1, 1),
('26141972', 'jerusalen', 'morillo', 'quibor', 'morillobetsy@gmail.com', '04245307390', 1, 0),
('34123567', 'angel', 'freitez', 'pueblo nuevo', 'angel@gmail.com', '04245624309', 1, 1),
('9154005', 'PEDRO JOSÉ', 'ARROYO ANDRADEZ', 'cabudare', 'pedro@gmail.com', '04145122345', 1, 1);

--
-- Disparadores `clientes`
--
DELIMITER $$
CREATE TRIGGER `cliente_insert_before` BEFORE INSERT ON `clientes` FOR EACH ROW begin
	IF LENGTH(NEW.cedula) < 7 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	if new.cedula REGEXP '[.;,"!#$%&?=@a-zA-z]{1}' <> 0 then
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	if new.nombres REGEXP '[.;,"!#$%&?=@0-9]{1}'  <> 0 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	if new.apellidos REGEXP '[.;,"!#$%&?=@0-9]{1}' <> 0 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	if LENGTH(NEW.nombres) < 3 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	if LENGTH(NEW.apellidos) < 3 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	if LENGTH(NEW.telefono) <> 11 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	if new.telefono REGEXP '[,;."!#$%&?=@a-zA-Z]{1}' <> 0 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	if new.correo REGEXP '[.@]{1}' <> 1 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	END IF;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `cliente_update_before` BEFORE UPDATE ON `clientes` FOR EACH ROW BEGIN
	IF LENGTH(NEW.cedula) < 7 THEN
		call Error('Error de tipos en la tabla empleados');
	end if;
    if new.cedula REGEXP '[.;,"!#$%&?=@a-zA-z]{1}' <> 0 then
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	IF LENGTH(NEW.nombres) < 3 THEN
		call Error('Error de tipos en la tabla empleados');
	end if;
	IF LENGTH(NEW.apellidos) < 3 THEN
		call Error('Error de tipos en la tabla empleados');
	end if;
	if new.nombres REGEXP '[.;,"!#$%&?=@0-9]{1}' <> 0 THEN
		call Error('Error en tipos de datos en la tabla empleados');
	end if;
	if new.apellidos REGEXP '[.;,"!#$%&?=@0-9]{1}' <> 0 THEN
		call Error('Error en tipos de datos en la tabla empleado');
	end if;
	IF LENGTH(NEW.telefono) <> 11 THEN
		call Error('Error en tipos de datos en la tabla empleado');
	end if;
	if new.telefono REGEXP '[,;."!#$%&?=@a-zA-Z]{1}' <> 0 THEN
		call Error('Error en tipos de datos en la tabla empleados');
	end if;
	if new.correo REGEXP '[.@]{1}' <> 1 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_factura`
--

CREATE TABLE `detalle_factura` (
  `numero_factura` int(11) NOT NULL,
  `cod_producto` varchar(50) NOT NULL,
  `cant` int(11) NOT NULL,
  `precio_venta` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `detalle_factura`
--

INSERT INTO `detalle_factura` (`numero_factura`, `cod_producto`, `cant`, `precio_venta`) VALUES
(1, '123456', 12, 600),
(1, '123456789', 3, 6000),
(1, '123456789123', 1, 1500),
(2, '123456', 5, 250),
(2, '123456789', 2, 4000),
(2, '123456789123456', 2, 10000),
(3, '123456', 2, 3000),
(3, '123456789', 2, 3000),
(3, '123456789123', 2, 3000),
(3, '123456789123456', 2, 3000),
(3, '354646', 2, 3000);

--
-- Disparadores `detalle_factura`
--
DELIMITER $$
CREATE TRIGGER `detalle_factura_insert_before` BEFORE INSERT ON `detalle_factura` FOR EACH ROW BEGIN
	IF NEW.cant <= 0 THEN
		call Error('La cantidad en la tabla detalle_factura no puede ser igual o menor a cero');
	end if;
	IF NEW.precio_venta <= 0 THEN
		call Error('El precio_venta en la tabla detalle_factura no puede ser igual o menor a cero');
	END IF;
	
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `detalle_factura_update_before` BEFORE UPDATE ON `detalle_factura` FOR EACH ROW BEGIN
	IF NEW.cant <= 0 THEN
		call Error('La cantidad en la tabla detalle_factura no puede ser igual o menor a cero');
	end if;
	IF NEW.precio_venta <= 0 THEN
		call Error('El precio_venta en la tabla detalle_factura no puede ser igual o menor a cero');
	END IF;
	
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `cedula` varchar(8) NOT NULL,
  `nombres` varchar(32) NOT NULL,
  `apellidos` varchar(32) NOT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `telefono` varchar(11) DEFAULT NULL,
  `tipo_telefono` int(11) DEFAULT NULL,
  `genero` char(1) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`cedula`, `nombres`, `apellidos`, `correo`, `telefono`, `tipo_telefono`, `genero`, `status`) VALUES
('12345678', 'yon', 'dilinger', 'yon@gmail.com', '04164567896', 1, 'M', 1),
('23918948', 'JERRINSON JOSE', 'GARCIA CALZADA', NULL, '04265897485', 1, 'M', 1),
('24157565', 'DAMELYS CORALIS', 'CARRILLO GIMENEZ', 'damelyscarrillo@gmail.com', '04241549685', 1, 'F', 1),
('25142034', 'YORLYS JOSE', 'OROPEZA ESCALONA', 'yorlysoro@gmail.com', '04262011594', 1, 'M', 1);

--
-- Disparadores `empleados`
--
DELIMITER $$
CREATE TRIGGER `empleado_insert_before` BEFORE INSERT ON `empleados` FOR EACH ROW BEGIN
	IF LENGTH(NEW.cedula) < 7 THEN
		call Error('Error de tipos en la tabla empleados');
	end if;
    if new.cedula REGEXP '[.;,"!#$%&?=@a-zA-z]{1}' <> 0 then
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	IF LENGTH(NEW.nombres) < 3 THEN
		call Error('Error de tipos en la tabla empleados');
	end if;
	IF LENGTH(NEW.apellidos) < 3 THEN
		call Error('Error de tipos en la tabla empleados');
	end if;
	if new.nombres REGEXP '[.;,"!#$%&?=@0-9]{1}' <> 0 THEN
		call Error('Error en tipos de datos en la tabla empleados');
	end if;
	if new.apellidos REGEXP '[.;,"!#$%&?=@0-9]{1}' <> 0 THEN
		call Error('Error en tipos de datos en la tabla empleado');
	end if;
	IF LENGTH(NEW.telefono) <> 11 THEN
		call Error('Error en tipos de datos en la tabla empleado');
	end if;
	if new.telefono REGEXP '[,;."!#$%&?=@a-zA-Z]{1}' <> 0 THEN
		call Error('Error en tipos de datos en la tabla empleados');
	end if;
	if new.correo REGEXP '[.@]{1}' <> 1 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `empleado_update_before` BEFORE UPDATE ON `empleados` FOR EACH ROW BEGIN
	IF LENGTH(NEW.cedula) < 7 THEN
		call Error('Error de tipos en la tabla empleados');
	end if;
    if regexp_like(new.cedula, '[.;,"!#$%&?=@a-zA-z]{1}' ) <> 0 then
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	IF LENGTH(NEW.nombres) < 3 THEN
		call Error('Error de tipos en la tabla empleados');
	end if;
	IF LENGTH(NEW.apellidos) < 3 THEN
		call Error('Error de tipos en la tabla empleados');
	end if;
	if regexp_like(new.nombres, '[.;,"!#$%&?=@0-9]{1}' ) <> 0 THEN
		call Error('Error en tipos de datos en la tabla empleados');
	end if;
	if regexp_like(new.apellidos, '[.;,"!#$%&?=@0-9]{1}' ) <> 0 THEN
		call Error('Error en tipos de datos en la tabla empleado');
	end if;
	IF LENGTH(NEW.telefono) <> 11 THEN
		call Error('Error en tipos de datos en la tabla empleado');
	end if;
	if regexp_like(new.telefono, '[,;."!#$%&?=@a-zA-Z]{1}' ) <> 0 THEN
		call Error('Error en tipos de datos en la tabla empleados');
	end if;
	if regexp_like(new.correo, '[.@]{1}' ) <> 1 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `cod_factura` int(11) NOT NULL,
  `ced_cliente` varchar(8) NOT NULL,
  `fecha` date NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`cod_factura`, `ced_cliente`, `fecha`, `status`) VALUES
(1, '9154005', '2019-07-01', 1),
(2, '11262635', '2019-07-01', 1),
(3, '17728034', '2019-07-01', 1),
(4, '25142034', '2018-11-01', 0),
(5, '123456', '2018-11-01', 0);

--
-- Disparadores `factura`
--
DELIMITER $$
CREATE TRIGGER `factura_insert_before` BEFORE INSERT ON `factura` FOR EACH ROW BEGIN
	IF LENGTH(NEW.ced_cliente) < 7 THEN
		call Error('Error de tipos en la tabla factura');
	end if;
    if new.ced_cliente REGEXP '[.;,"!#$%&?=@a-zA-z]{1}' <> 0 then
		call Error('Error en tipos de datos en la tabla factura');
	end if;

	
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `fatura_update_before` BEFORE UPDATE ON `factura` FOR EACH ROW BEGIN
	IF LENGTH(NEW.ced_cliente) < 7 THEN
		call Error('Error de tipos en la tabla factura');
	end if;
    if new.ced_cliente REGEXP '[.;,"!#$%&?=@a-zA-z]{1}' <> 0 then
		call Error('Error en tipos de datos en la tabla factura');
	end if;

	
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas_seguridad`
--

CREATE TABLE `preguntas_seguridad` (
  `Cod` int(11) NOT NULL,
  `Cedula_empleado` varchar(8) NOT NULL,
  `Pregunta` mediumtext NOT NULL,
  `Respuesta` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `preguntas_seguridad`
--

INSERT INTO `preguntas_seguridad` (`Cod`, `Cedula_empleado`, `Pregunta`, `Respuesta`) VALUES
(3, '12345678', '¿Cual es su pasatiempo favorito?', 'musica'),
(4, '12345678', '¿Cual es el segundo nombre de su madre?', 'corina');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `codigo` varchar(50) NOT NULL,
  `nombre` varchar(32) NOT NULL,
  `Descripcion` text NOT NULL,
  `Precio` double NOT NULL,
  `Categorias` int(11) NOT NULL,
  `Cant` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`codigo`, `nombre`, `Descripcion`, `Precio`, `Categorias`, `Cant`, `status`) VALUES
('123456', 'Pan Salado', 'Relleno con Queso', 50, 1, 25, 1),
('123456789', 'Empanada', 'Empanada rellena con queso', 2000, 1, 80, 1),
('123456789123', 'Jugo Natural', 'Jugo de uva', 1500, 2, 10, 1),
('123456789123456', 'Samba', 'Producto Samba de colombina', 5000, 3, 30, 1),
('354646', 'caraotas', 'caraotas negras', 600, 1, 100, 1);

--
-- Disparadores `productos`
--
DELIMITER $$
CREATE TRIGGER `producto_insert_before` BEFORE INSERT ON `productos` FOR EACH ROW BEGIN
	IF LENGTH(NEW.nombre) < 3 THEN
		call Error('Error de tipos en la tabla productos');
	end if;
	if new.nombre REGEXP '[.;,"!#$%&?=@0-9]{1}' <> 0 THEN
		call Error('Error en tipos de datos en la tabla productos');
	end if;
	IF NEW.Cant < 0 THEN
		call Error('Error la cantidad no puede ser menor a cero');
	end if;
	IF NEW.precio <= 0  THEN
		call Error('Error el precio no puede ser igual o menor a cero');
	END IF;
	
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `producto_update_before` BEFORE UPDATE ON `productos` FOR EACH ROW BEGIN
	IF LENGTH(NEW.nombre) < 3 THEN
		call Error('Error de tipos en la tabla productos');
	end if;
	if new.nombre REGEXP '[.;,"!#$%&?=@0-9]{1}' <> 0 THEN
		call Error('Error en tipos de datos en la tabla productos');
	end if;
	IF NEW.Cant < 0 THEN
		call Error('Error la cantidad no puede ser menor a cero');
	end if;
	IF NEW.precio <= 0  THEN
		call Error('Error el precio no puede ser igual o menor a cero');
	END IF;
	
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `status`
--

CREATE TABLE `status` (
  `cod` tinyint(1) NOT NULL,
  `nombre` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `status`
--

INSERT INTO `status` (`cod`, `nombre`) VALUES
(0, 'INACTIVO'),
(1, 'ACTIVO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_usuarios`
--

CREATE TABLE `tipos_usuarios` (
  `cod` int(11) NOT NULL,
  `nombre` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipos_usuarios`
--

INSERT INTO `tipos_usuarios` (`cod`, `nombre`) VALUES
(1, 'ADMINISTRADOR'),
(2, 'CAJERO'),
(3, 'INVENTARIO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_telefono`
--

CREATE TABLE `tipo_telefono` (
  `cod` int(11) NOT NULL,
  `nombre` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo_telefono`
--

INSERT INTO `tipo_telefono` (`cod`, `nombre`) VALUES
(1, 'Movil'),
(2, 'Casa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `cedula_empleado` varchar(8) NOT NULL,
  `nombre_usuario` varchar(32) NOT NULL,
  `contrasena` longtext NOT NULL,
  `cod_nivel` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`cedula_empleado`, `nombre_usuario`, `contrasena`, `cod_nivel`, `status`) VALUES
('12345678', 'yon', '12345678', 1, 1),
('23918948', 'jerry', 'Jerry123#', 3, 1),
('24157565', 'damelys', 'Damelys123#', 2, 1),
('25142034', 'yorlys', 'Yorlys123#', 1, 1);

--
-- Disparadores `usuarios`
--
DELIMITER $$
CREATE TRIGGER `usuario_insert_before` BEFORE INSERT ON `usuarios` FOR EACH ROW BEGIN
		IF LENGTH(NEW.cedula_empleado) < 7 THEN
		call Error('Error de tipos en la tabla factura');
	end if;
    if new.cedula_empleado REGEXP '[.;,"!#$%&?=@a-zA-z]{1}' <> 0 then
		call Error('Error en tipos de datos en la tabla usuarios');
	end if;
	IF LENGTH(NEW.nombre_usuario) < 3 THEN
		call Error('Error de tipos en la tabla usuarios');
	end if;
	if new.nombre_usuario REGEXP '[.;,"!#$%&?=@0-9]{1}' <> 0 THEN
		call Error('Error en tipos de datos en la tabla usuarios');
	end if;
	IF LENGTH(NEW.contrasena) < 8 THEN
		call Error('La contraseña debe contener 8 o mas caracters');
	end if;
	IF NEW.contrasena REGEXP '[=a-zA-Z]{3,3}'  = 0 THEN
		call Error('La contraseña debe contener como minimo tres letras');
	end if;
	IF NEW.contrasena REGEXP '[.;,"!#$%&?=@]{1}' = 0 THEN
		call Error('La contraseña debe contener como minimo un carater especial');
	end if;
	IF NEW.contrasena REGEXP '[=0-9=]{3,3}' = 0 THEN
		call Error('La contraseña debe contener como minimo tres digitos');
	END IF;
	
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `usuario_update_before` BEFORE UPDATE ON `usuarios` FOR EACH ROW BEGIN
		IF LENGTH(NEW.cedula_empleado) < 7 THEN
		call Error('Error de tipos en la tabla factura');
	end if;
    if new.cedula_empleado REGEXP '[.;,"!#$%&?=@a-zA-z]{1}' <> 0 then
		call Error('Error en tipos de datos en la tabla usuarios');
	end if;
	IF LENGTH(NEW.nombre_usuario) < 3 THEN
		call Error('Error de tipos en la tabla usuarios');
	end if;
	if new.nombre_usuario REGEXP '[.;,"!#$%&?=@0-9]{1}' <> 0 THEN
		call Error('Error en tipos de datos en la tabla usuarios');
	end if;
	IF LENGTH(NEW.contrasena) < 8 THEN
		call Error('La contraseña debe contener 8 o mas caracters');
	end if;
	IF NEW.contrasena REGEXP '[=a-zA-Z]{3,3}'  = 0 THEN
		call Error('La contraseña debe contener como minimo tres letras');
	end if;
	IF NEW.contrasena REGEXP '[.;,"!#$%&?=@]{1}' = 0 THEN
		call Error('La contraseña debe contener como minimo un carater especial');
	end if;
	IF NEW.contrasena REGEXP '[=0-9=]{3,3}' = 0 THEN
		call Error('La contraseña debe contener como minimo tres digitos');
	END IF;
	
END
$$
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `acciones`
--
ALTER TABLE `acciones`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `actividad_usuarios`
--
ALTER TABLE `actividad_usuarios`
  ADD PRIMARY KEY (`cod`),
  ADD KEY `accion` (`Accion`),
  ADD KEY `cod_usuario` (`cod_usuario`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cedula`),
  ADD KEY `tipo_telefono2` (`tipo_telefono`),
  ADD KEY `Status_Relacion` (`status`);

--
-- Indices de la tabla `detalle_factura`
--
ALTER TABLE `detalle_factura`
  ADD KEY `numero_factura` (`numero_factura`),
  ADD KEY `cod_producto` (`cod_producto`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`cedula`),
  ADD KEY `tipo_telefono` (`tipo_telefono`),
  ADD KEY `status_relacion2` (`status`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`cod_factura`),
  ADD KEY `ced_cliente` (`ced_cliente`),
  ADD KEY `status_relacion3` (`status`);

--
-- Indices de la tabla `preguntas_seguridad`
--
ALTER TABLE `preguntas_seguridad`
  ADD PRIMARY KEY (`Cod`),
  ADD KEY `Relacion_Empleado` (`Cedula_empleado`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `status_relacion4` (`status`),
  ADD KEY `categoria_relacion` (`Categorias`);

--
-- Indices de la tabla `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `tipos_usuarios`
--
ALTER TABLE `tipos_usuarios`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `tipo_telefono`
--
ALTER TABLE `tipo_telefono`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`cedula_empleado`),
  ADD KEY `cedula_empleado` (`cedula_empleado`),
  ADD KEY `relacion_tipo` (`cod_nivel`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `acciones`
--
ALTER TABLE `acciones`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `actividad_usuarios`
--
ALTER TABLE `actividad_usuarios`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `preguntas_seguridad`
--
ALTER TABLE `preguntas_seguridad`
  MODIFY `Cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tipos_usuarios`
--
ALTER TABLE `tipos_usuarios`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipo_telefono`
--
ALTER TABLE `tipo_telefono`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividad_usuarios`
--
ALTER TABLE `actividad_usuarios`
  ADD CONSTRAINT `accion` FOREIGN KEY (`Accion`) REFERENCES `acciones` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cod_usuario` FOREIGN KEY (`cod_usuario`) REFERENCES `usuarios` (`cedula_empleado`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `Status_Relacion` FOREIGN KEY (`status`) REFERENCES `status` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tipo_telefono2` FOREIGN KEY (`tipo_telefono`) REFERENCES `tipo_telefono` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalle_factura`
--
ALTER TABLE `detalle_factura`
  ADD CONSTRAINT `cod_producto` FOREIGN KEY (`cod_producto`) REFERENCES `productos` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `numero_factura` FOREIGN KEY (`numero_factura`) REFERENCES `factura` (`cod_factura`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `status_relacion2` FOREIGN KEY (`status`) REFERENCES `status` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tipo_telefono` FOREIGN KEY (`tipo_telefono`) REFERENCES `tipo_telefono` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `ced_cliente` FOREIGN KEY (`ced_cliente`) REFERENCES `clientes` (`cedula`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `status_relacion3` FOREIGN KEY (`status`) REFERENCES `status` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `preguntas_seguridad`
--
ALTER TABLE `preguntas_seguridad`
  ADD CONSTRAINT `Relacion_Empleado` FOREIGN KEY (`Cedula_empleado`) REFERENCES `empleados` (`cedula`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `categoria_relacion` FOREIGN KEY (`Categorias`) REFERENCES `categoria` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `status_relacion4` FOREIGN KEY (`status`) REFERENCES `status` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `cedula_empleado` FOREIGN KEY (`cedula_empleado`) REFERENCES `empleados` (`cedula`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `relacion_tipo` FOREIGN KEY (`cod_nivel`) REFERENCES `tipos_usuarios` (`cod`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
