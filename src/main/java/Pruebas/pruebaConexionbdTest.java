package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sagmade.config.Conexion;

class pruebaConexionbdTest {

	@Test
    public void testConexionEstablecida() {
        // Crear una instancia de la clase Conexion
        Conexion conexion = new Conexion();
        
        // Invocar el método para obtener la conexión
        Object conexionResultado = conexion.getConnection();
        
        // Verificar que la conexión no sea nula
        assertNotNull(conexionResultado, "La conexión a la base de datos debería ser válida.");
    }
}
