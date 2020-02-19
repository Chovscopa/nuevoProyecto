create database registro_pasos;
create table usuarios(
	usuario VARCHAR(20) NOT NULL PRIMARY KEY UNIQUE,
	apellido VARCHAR(200) NOT NULL,
	fecha VARCHAR(200),
	departamento VARCHAR(200),
	salario VARCHAR(200),
	comentarios VARCHAR(200),
	cuenta VARCHAR(200)
);
INSERT INTO usuarios VALUES(usuario,apellido,fecha,departamento,salario,cuenta);