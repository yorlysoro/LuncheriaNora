/*
 * comand_usuarios.sql
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
CREATE USER 'yorlys'@'localhost' IDENTIFIED BY 'contrasena';

CREATE USER 'damelys'@'localhost' IDENTIFIED BY 'damelys';

CREATE USER 'wilmarys'@'localhost' IDENTIFIED BY 'wilmarys';

/*Administrador*/

GRANT ALL PRIVILEGES ON *.* TO 'yorlys'@'localhost';

/*Cajero*/

GRANT SELECT, INSERT, CREATE, ALTER, DELETE,  UPDATE ON Luncheria.clientes TO 'damelys'@'localhost';
GRANT SELECT, INSERT, CREATE, ALTER, DELETE,  UPDATE ON Luncheria.detalle_factura TO 'damelys'@'localhost';
GRANT SELECT, INSERT, CREATE, ALTER, DELETE,  UPDATE ON Luncheria.factura TO 'damelys'@'localhost';
/*Inventario*/
GRANT SELECT, INSERT, CREATE, ALTER, DELETE, UPDATE ON Luncheria.productos TO 'wilmarys'@'localhost';

/*Politicas de contraseñas*/
ALTER USER 'yorlys'@'localhost' PASSWORD EXPIRE;
ALTER USER 'damelys'@'localhost' PASSWORD EXPIRE;
ALTER USER 'wilmarys'@'localhost' PASSWORD EXPIRE;
