package Pruebas;

import Config.Conexion;

public class PruebaConexionbd {

	public static void main(String[] args) {
		
		Conexion conexion = new Conexion();
		
		//Invocacion de metodos para conexion a la BD
		conexion.getConnection();

	}

}
