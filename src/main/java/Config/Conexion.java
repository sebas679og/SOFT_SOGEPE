package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String URL = "jdbc:mysql://localhost:3306/sogepe?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "sebas1234";
    private static Connection connection = null;
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Conexion exitosa a la base de datos");
			} catch (ClassNotFoundException e) {
				System.out.println("Error al registrar el driver de MySQL: " + e.getMessage());
			} catch (SQLException e) {
				System.out.println("Error al conectar a la base de datos: " + e.getMessage());
			}
		}
		return connection;
	}

}
