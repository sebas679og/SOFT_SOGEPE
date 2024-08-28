package Pruebas;

import java.sql.SQLException;

import com.sagmade.config.Conexion;

public class PruebaConexionbd {

	public static void main(String[] args) throws SQLException {
		
		Conexion conexion = new Conexion();
		
		//Invocacion de metodos para conexion a la BD
		Conexion.getConnection();

	}

}
