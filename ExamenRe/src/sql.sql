CREATE DATABASE IF NOT EXISTS examenservidor DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE examenservidor;



CREATE TABLE cuentas (
         ncuenta  CHAR(24) NOT NULL PRIMARY KEY,
         saldo DECIMAL(9,2)
           );

CREATE TABLE movimientos (
         id mediumint NOT NULL PRIMARY KEY auto_increment,
         fecha date not null,
         ncuentaorigen char(24) NOT NULL,
         ncuentadestino char(24) NOT NULL,
         cantidad decimal(9,2)
           );



INSERT INTO cuentas  VALUES
        ("ES7101969090090745654000",210.00),
        ("ES9100988989098745654000",40.45),        
        ("ES8100912890098000674000",5000.07),
        ("ES9100981230044745647000",28000),
        ("ES7100989812345745674000",-300.00),
        ("ES6100989890098005674000",25.00),
        ("ES5100549730098745674000",25.00);
COMMIT;