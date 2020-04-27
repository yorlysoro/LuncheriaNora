/*
 * consultas.sql
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
/*Consultas avanazdas usando combinaciones internas INNER JOIN*/
select f.cod_factura, f.fecha, 
	cl.cedula, cl.apellidos, cl.nombres, cl.direccion,
	p.codigo as codigo_producto, p.nombre as nombre_producto, p.Descripcion as Descripcion_producto, p.Precio, 
	ca.nombre as Categoria,
	df.precio_venta, df.cant as Cantidad, 
	st.nombre as estado FROM factura f
	INNER JOIN detalle_factura df
	ON  f.cod_factura = df.numero_factura
	INNER JOIN clientes cl
	ON f.ced_cliente =  cl.cedula
    INNER JOIN productos p
    ON df.cod_producto = p.codigo
    inner join categoria ca
    ON p.Categorias = ca.cod
    inner join status st
    on f.status = st.cod
	ORDER BY f.cod_factura;
	
/*SUBCONSULTAS*/

select e.cedula, e.nombres, e.apellidos, e.correo, e.telefono,
		u.nombre_usuario,
		tu.nombre as nivel,
		st.nombre as Estado FROM empleados e, tipo_telefono tt, usuarios u, tipos_usuarios tu, status st
		WHERE e.cedula IN (select cedula_empleado FROM usuarios)
		AND u.cedula_empleado = e.cedula
        AND u.cod_nivel = tu.cod
        AND st.status IN (select status from empleados)
        GROUP BY e.cedula
		ORDER BY e.cedula;
	
/*consultas simples*/

select cedula, apellidos, nombres, correo from empleados;
select cedula_empleado, nombre_usuario from usuarios;

