/*
 * usuario_update_before.sql
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

DELIMITER $$
CREATE TRIGGER usuario_update_before
before UPDATE ON usuarios
FOR EACH ROW
BEGIN
IF LENGTH(NEW.cedula_empleado) < 7 THEN
		call Error('Error de tipos en la tabla factura');
	end if;
    if regexp_like(new.cedula_empleado, '[.;,"!#$%&?=@a-zA-z]{1}' ) <> 0 then
		call Error('Error en tipos de datos en la tabla usuarios');
	end if;
	IF LENGTH(NEW.nombre_usuario) < 3 THEN
		call Error('Error de tipos en la tabla usuarios');
	end if;
	if regexp_like(new.nombre_usuario, '[.;,"!#$%&?=@0-9]{1}' ) <> 0 THEN
		call Error('Error en tipos de datos en la tabla usuarios');
	end if;
	IF LENGTH(NEW.contrasena) < 8 THEN
		call Error('La contraseña debe contener 8 o mas caracters');
	end if;
	IF REGEXP_LIKE(NEW.contrasena, '[=a-zA-Z]{3,3}')  = 0 THEN
		call Error('La contraseña debe contener como minimo tres letras');
	end if;
	IF REGEXP_LIKE(NEW.contrasena, '[.;,"!#$%&?=@]{1}') = 0 THEN
		call Error('La contraseña debe contener como minimo un carater especial');
	end if;
	IF REGEXP_LIKE(NEW.contrasena, '[=0-9=]{3,3}') = 0 THEN
		call Error('La contraseña debe contener como minimo tres digitos');
	END IF;
END$$
DELIMITER ;
