package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.sagmade.config.Conexion;

class pruebaConexionbdTest {

	@Test
    public void testConexionEstablecida() throws SQLException {
        // Crear una instancia de la clase Conexion
        Conexion conexion = new Conexion();
        
        // Invocar el método para obtener la conexión
        Object conexionResultado = Conexion.getConnection();
        
        // Verificar que la conexión no sea nula
        assertNotNull(conexionResultado, "La conexión a la base de datos debería ser válida.");
    }
}
