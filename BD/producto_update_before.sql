/*
 * producto_update_before.sql
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
CREATE TRIGGER producto_update_before
before UPDATE ON productos
FOR EACH ROW
BEGIN
	IF LENGTH(NEW.nombre) < 3 THEN
		call Error('Error de tipos en la tabla productos');
	end if;
	if regexp_like(new.nombre, '[.;,"!#$%&?=@0-9]{1}' ) <> 0 THEN
		call Error('Error en tipos de datos en la tabla productos');
	end if;
	IF NEW.Cant < 0 THEN
		call Error('Error la cantidad no puede ser menor a cero');
	end if;
	IF NEW.precio <= 0  THEN
		call Error('Error el precio no puede ser igual o menor a cero');
	END IF;
	
END$$
DELIMITER ;
