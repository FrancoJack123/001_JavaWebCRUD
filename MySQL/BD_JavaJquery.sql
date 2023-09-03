CREATE DATABASE proyect_crud_ajax;
USE proyect_crud_ajax;

DROP TABLE IF EXISTS tb_categoria;
CREATE TABLE tb_categoria (
  cod_categoria int NOT NULL AUTO_INCREMENT,
  descrip_categ varchar(50) DEFAULT NULL,
  PRIMARY KEY (cod_categoria)
);

INSERT INTO tb_categoria(descrip_categ) VALUES
("Frutas"),
("Verduras");

DROP TABLE IF EXISTS tb_producto;
CREATE TABLE tb_producto (
  cod_producto int NOT NULL AUTO_INCREMENT,
  cod_categoria int,
  descrip_prod varchar(50) DEFAULT NULL,
  stock int DEFAULT NULL,
  pventa double DEFAULT NULL,
  fecha_venc datetime,
  activo bit not null,
  PRIMARY KEY (cod_producto),
  FOREIGN KEY (cod_categoria) REFERENCES tb_categoria(cod_categoria)
);

INSERT INTO tb_producto(cod_categoria, descrip_prod, stock, pventa, fecha_venc, activo) VALUES
(1, "Manzana Gala", 100, 0.99, '2023-08-31 23:59:59', 1),
(1, "Plátano", 150, 0.49, '2023-08-31 23:59:59', 1),
(1, "Naranjas (Bolsa de 10 unidades)", 80, 3.99, '2023-08-31 23:59:59', 1),
(1, "Uvas Rojas (500g)", 40, 2.49, '2023-08-31 23:59:59', 1);

INSERT INTO tb_producto(cod_categoria, descrip_prod, stock, pventa, fecha_venc, activo) VALUES
(2, "Zanahorias (Bolsa de 1kg)", 60, 1.99, '2023-08-31 23:59:59', 1),
(2, "Lechuga Romana", 30, 0.89, '2023-08-31 23:59:59', 1),
(2, "Tomates (Bolsa de 6 unidades)", 50, 2.29, '2023-08-31 23:59:59', 1),
(2, "Espinaca Fresca (200g)", 20, 1.49, '2023-08-31 23:59:59', 1);

//INSERT INTO tb_producto VALUES(NULL, ?, ?, ?, ?, ?, 1);

//select max(cod_producto) from tb_producto;
//SELECT * FROM tb_categoria where activo = 1;
//SELECT * FROM tb_producto where cod_producto = 1;

//SELECT p.*, c.descrip_categ
//FROM tb_producto p
//INNER JOIN tb_categoria c ON p.cod_categoria = c.cod_categoria where activo = 1;


//SELECT * FROM tb_producto where cod_producto = 1;
//INSERT INTO tb_producto VALUES(NULL, 'Pollo', '100', 2.99, '2023-06-30');

//UPDATE tb_producto SET cod_categoria = ?, descrip_prod = ?, stock = ?, pventa = ?, fecha_venc = ?, activo= 1 WHERE cod_producto = ?;
//UPDATE tb_producto SET activo= 1 WHERE cod_producto = 8