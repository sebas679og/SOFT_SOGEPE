package PruebasJUnit;

// Importación de librerías necesarias para las pruebas
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.sagmade.config.Conexion;
import com.sagmade.dao.ModuloUsuarios;
import com.sagmade.model.ListarUsuarios;
import com.sagmade.model.T_Personas;
import com.sagmade.model.T_Usuarios;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Clase de prueba para la clase ModuloUsuarios
class ModuloUsuariosTest {

    @InjectMocks
    private ModuloUsuarios moduloUsuarios;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        // Mockear el método estático Conexion.getConnection() para que devuelva la conexión mockeada
        mockStatic(Conexion.class);
        when(Conexion.getConnection()).thenReturn(connection);

        // Mockear el comportamiento del preparedStatement
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    // Prueba del método eliminarUsuario
    @Test
    void testEliminarUsuario() throws SQLException {
        // Dado: parámetros para la prueba
        int idUsuarios = 1;
        int idPersonas = 1;

        // Cuando: se llama al método eliminarUsuario
        moduloUsuarios.eliminarUsuario(idUsuarios, idPersonas);

        // Entonces: verifica que se han realizado las llamadas esperadas al PreparedStatement
        verify(preparedStatement).setInt(1, idUsuarios);
        verify(preparedStatement).setInt(2, idPersonas); // Corrección aquí: suponiendo que se usa el índice 2
        verify(preparedStatement).executeUpdate();
    }

    // Prueba del método actualizarUsuario
    @Test
    void testActualizarUsuario() throws SQLException {
        // Dado: instancias de T_Personas y T_Usuarios con datos de prueba
        T_Personas tpersona = new T_Personas();
        T_Usuarios tusuario = new T_Usuarios();
        
        // Configura los datos necesarios en tpersona y tusuario
        tpersona.setTipoDocumento(1);
        tpersona.setNumeroIdentificacion(123456);
        tpersona.setPrimerNombre("John");
        tpersona.setSegundoNombre("Doe");
        tpersona.setPrimerApellido("Smith");
        tpersona.setSegundoApellido("Jones");
        tpersona.setTelefono("123456789");
        tpersona.setDireccion("123 Street");
        tpersona.setGenero(1);
        tpersona.setIdPersonas(1);

        tusuario.setUsername("johndoe");
        tusuario.setContraseña("password");
        tusuario.setCorreo("john.doe@example.com");
        tusuario.setEstadoUsuario(1);
        tusuario.setRol(1);
        tusuario.setIdUsuarios(1);

        // Cuando: se llama al método actualizarUsuario
        moduloUsuarios.actualizarUsuario(tpersona, tusuario);

        // Entonces: verifica que se han realizado las llamadas esperadas al PreparedStatement para T_Personas
        verify(preparedStatement).setInt(1, tpersona.getTipoDocumento());
        verify(preparedStatement).setInt(2, tpersona.getNumeroIdentificacion());
        verify(preparedStatement).setString(3, tpersona.getPrimerNombre());
        verify(preparedStatement).setString(4, tpersona.getSegundoNombre());
        verify(preparedStatement).setString(5, tpersona.getPrimerApellido());
        verify(preparedStatement).setString(6, tpersona.getSegundoApellido());
        verify(preparedStatement).setString(7, tpersona.getTelefono());
        verify(preparedStatement).setString(8, tpersona.getDireccion());
        verify(preparedStatement).setInt(9, tpersona.getGenero());
        verify(preparedStatement).setInt(10, tpersona.getIdPersonas());

        // Verifica llamadas para T_Usuarios
        verify(preparedStatement).setString(1, tusuario.getUsername());
        verify(preparedStatement).setString(2, tusuario.getContraseña());
        verify(preparedStatement).setString(3, tusuario.getCorreo());
        verify(preparedStatement).setInt(4, tusuario.getEstadoUsuario());
        verify(preparedStatement).setInt(5, tusuario.getRol());
        verify(preparedStatement).setInt(6, tusuario.getIdUsuarios());
    }

    // Prueba del método listAllUsers
    @Test
    void testListAllUsers() throws SQLException {
        // Dado: lista de usuarios esperada
        List<ListarUsuarios> expectedList = new ArrayList<>();
        expectedList.add(new ListarUsuarios(1, "CC", 123456, "Admin", "Activo", 1, "user1"));

        // Simula la preparación del statement y el resultado de la consulta
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id_usuarios")).thenReturn(1);
        when(resultSet.getString("tipo_documentos")).thenReturn("CC");
        when(resultSet.getInt("numeroIdentificacion")).thenReturn(123456);
        when(resultSet.getString("roles")).thenReturn("Admin");
        when(resultSet.getString("estadoUsuario")).thenReturn("Activo");
        when(resultSet.getInt("id_personas")).thenReturn(1);
        when(resultSet.getString("username")).thenReturn("user1");

        // Cuando: se llama al método listAllUsers
        List<ListarUsuarios> actualList = moduloUsuarios.listAllUsers();

        // Entonces: verifica que la lista obtenida es igual a la lista esperada
        assertEquals(expectedList, actualList);
    }

    // Prueba del método insertarUsuario
    @Test
    void testInsertarUsuario() throws SQLException {
        // Dado: instancias de T_Personas y T_Usuarios con datos de prueba
        T_Personas tpersonas = new T_Personas();
        T_Usuarios tusuarios = new T_Usuarios();
        tpersonas.setTipoDocumento(1);
        tpersonas.setNumeroIdentificacion(123456);
        tpersonas.setPrimerNombre("John");
        tpersonas.setSegundoNombre("Doe");
        tpersonas.setPrimerApellido("Smith");
        tpersonas.setSegundoApellido("Jones");
        tpersonas.setTelefono("123456789");
        tpersonas.setDireccion("123 Street");
        tpersonas.setGenero(1);

        tusuarios.setUsername("johndoe");
        tusuarios.setContraseña("password");
        tusuarios.setCorreo("john.doe@example.com");
        tusuarios.setEstadoUsuario(1);
        tusuarios.setRol(1);

        // Simula la preparación del statement con retorno de claves generadas
        when(connection.prepareStatement(anyString(), eq(PreparedStatement.RETURN_GENERATED_KEYS)))
            .thenReturn(preparedStatement);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);

        // Cuando: se llama al método insertarUsuario
        moduloUsuarios.insertarUsuario(tpersonas, tusuarios);

        // Entonces: verifica que se han realizado las llamadas esperadas al PreparedStatement para T_Personas
        verify(preparedStatement).setInt(1, tpersonas.getTipoDocumento());
        verify(preparedStatement).setInt(2, tpersonas.getNumeroIdentificacion());
        verify(preparedStatement).setString(3, tpersonas.getPrimerNombre());
        verify(preparedStatement).setString(4, tpersonas.getSegundoNombre());
        verify(preparedStatement).setString(5, tpersonas.getPrimerApellido());
        verify(preparedStatement).setString(6, tpersonas.getSegundoApellido());
        verify(preparedStatement).setString(7, tpersonas.getTelefono());
        verify(preparedStatement).setString(8, tpersonas.getDireccion());
        verify(preparedStatement).setInt(9, tpersonas.getGenero());

        // Verifica llamadas para T_Usuarios
        verify(preparedStatement).setString(1, tusuarios.getUsername());
        verify(preparedStatement).setString(2, tusuarios.getContraseña());
        verify(preparedStatement).setString(3, tusuarios.getCorreo());
        verify(preparedStatement).setInt(4, tusuarios.getEstadoUsuario());
        verify(preparedStatement).setInt(5, tusuarios.getRol());
    }
}

