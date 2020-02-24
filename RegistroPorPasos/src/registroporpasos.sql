create database registro_pasos;
create table usuarios(
	usuario VARCHAR(20) NOT NULL PRIMARY KEY UNIQUE,
	apellido VARCHAR(20) NOT NULL,
	fecha VARCHAR(20),
	departamento VARCHAR(20),
	salario VARCHAR(20),
	comentarios VARCHAR(200),
	cuenta VARCHAR(20),
	genero VARCHAR(20),
	nacionalidad VARCHAR(200),
	casaOpareja VARCHAR(20),
	hijos VARCHAR(20)
);
DELETE FROM usuarios WHERE usuario = 'Ovidio';
INSERT INTO usuarios VALUES(usuario,apellido,fecha,departamento,salario,cuenta);