/*
 * cliente_insert_after.sql
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
 * * Neither the name of the yorlysoro nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
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
DELIMITER //
CREATE TRIGGER cliente_insert_before
before insert on clientes
for each row
begin
	IF LENGTH(NEW.cedula) < 7 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	if regexp_like(new.cedula, '[.;,"!#$%&?=@a-zA-z]{1}' ) <> 0 then
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	if regexp_like(new.nombres, '[.;,"!#$%&?=@0-9]{1}' ) <> 0 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	if regexp_like(new.apellidos, '[.;,"!#$%&?=@0-9]{1}' ) <> 0 THEN
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
	if regexp_like(new.telefono, '[,;."!#$%&?=@a-zA-Z]{1}' ) <> 0 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	end if;
	if regexp_like(new.correo, '[.@]{1}' ) <> 1 THEN
		call Error('Error en tipos de datos en la tabla clientes');
	END IF;
end
