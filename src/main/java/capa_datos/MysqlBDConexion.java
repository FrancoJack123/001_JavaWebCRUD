package capa_datos;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlBDConexion {
	static{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConexion(){
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost/proyect_crud_ajax?useTimezone=true&serverTimezone=UTC","root","");
                        System.out.println("Conexion exitosa");
		}catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Conexion erronea");
		}
		return con;
	}
	
	
		
}
