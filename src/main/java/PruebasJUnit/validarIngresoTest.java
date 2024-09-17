package PruebasJUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sagmade.dao.ValidarIngreso;

class validarIngresoTest {
	private ValidarIngreso validarIngreso;

    @BeforeEach
    public void setUp() throws SQLException {
        // Inicializa la instancia de ValidarIngreso antes de cada prueba
        validarIngreso = new ValidarIngreso();
    }

    @Test
    public void testValidarUsuarioValido() {
        String username = "sogepe";
        String contraseña = "admin";
        String tipoUsuario = "Administrador";

        boolean resultado = validarIngreso.validarUsuario(username, contraseña, tipoUsuario);

        // Se asume que el usuario es válido para este conjunto de datos
        assertTrue(resultado, "El usuario debería ser válido");
        
      //Impresion de resultado en Consola
        System.out.println(resultado);
    }

    @Test
    public void testValidarUsuarioInvalido() {
        String username = "sebasorjuelag";
        String contraseña = "incorrecta";
        String tipoUsuario = "Administrador";

        boolean resultado = validarIngreso.validarUsuario(username, contraseña, tipoUsuario);

        // Se asume que la contraseña incorrecta hace que el usuario sea inválido
        assertFalse(resultado, "El usuario debería ser inválido");
        
        //Impresion de resultado en Consola
        System.out.println(resultado);
    }

    @AfterEach
    public void tearDown() {
        // Aquí puedes realizar cualquier limpieza después de cada prueba, si es necesario
        validarIngreso = null;
    }

}