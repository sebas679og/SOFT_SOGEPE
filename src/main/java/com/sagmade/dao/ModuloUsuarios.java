package com.sagmade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sagmade.config.Conexion;
import com.sagmade.model.ListarUsuarios;
import com.sagmade.model.T_Personas;
import com.sagmade.model.T_Usuarios;

public class ModuloUsuarios {

	public ModuloUsuarios() {
		super();
	}
	
	private static final String INSERTAR_PERSONA = ("INSERT INTO personas (tipoDocumento, numeroIdentificacion, primerNombre, segundoNombre, "
			+ "primerApellido, segundoApellido, telefono, direccion, genero) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	
	private static final String INSERTAR_USUARIO = ("INSERT INTO usuarios (username, contraseña, correo, estadoUsuario, rol, persona) "
			+ "VALUES ( ?, ?, ?, ?, ?, ?)");
	
	private static final String LISTAR_USUARIOS = ("SELECT u.idUsuarios AS id_usuarios, td.sigla AS tipo_documentos, "
			+ "p.numeroIdentificacion, r.rol AS roles, eu.estado AS estadoUsuario, p.idPersonas AS id_personas, u.username AS username  "
			+ "FROM personas p "
			+ "JOIN usuarios u ON p.idPersonas = u.persona "
			+ "JOIN roles r ON u.rol = r.idRoles "
			+ "JOIN tipo_documentos td ON p.tipoDocumento = td.idTipo_Documentos "
			+ "JOIN estado_usuarios eu ON u.estadoUsuario = eu.idEstado_Usuarios;");
	
	private static final String LISTAR_USUARIO = ("SELECT u.idUsuarios AS id_usuarios, td.sigla AS tipo_documentos, p.numeroIdentificacion, "
			+ "r.rol AS roles, eu.estado AS estadoUsuario, p.idPersonas AS id_personas, u.username AS username "
			+ "FROM personas p "
			+ "JOIN usuarios u ON p.idPersonas = u.persona "
			+ "JOIN roles r ON u.rol = r.idRoles "
			+ "JOIN tipo_documentos td ON p.tipoDocumento = td.idTipo_Documentos "
			+ "JOIN estado_usuarios eu ON u.estadoUsuario = eu.idEstado_Usuarios "
			+ "WHERE p.numeroIdentificacion = ?;");
	
	private static final String ACTUALIZAR_PERSONA = ("UPDATE personas SET tipoDocumento = ?, numeroIdentificacion = ?, primerNombre = ?, "
			+ "segundoNombre = ?, primerApellido = ?, segundoApellido = ?, telefono = ?, direccion = ?, genero = ? WHERE idPersonas = ?");
	
	private static final String ACTUALIZAR_USUARIO = ("UPDATE usuarios SET username = ?, contraseña = ?, correo = ?, "
			+ "estadoUsuario = ?, rol = ? WHERE idUsuarios = ?");
	
	private static final String  ELIMINAR_USUARIO = ("DELETE FROM usuarios WHERE idUsuarios = ?");
	
	private static final String ELIMINAR_PERSONA = ("DELETE FROM personas WHERE idPersonas = ?");
	
	public void eliminarUsuario(int idUsuarios, int idPersonas) throws SQLException{
		try (Connection conn = Conexion.getConnection();
				PreparedStatement pstmtUsuarios = conn.prepareStatement(ELIMINAR_USUARIO);
				PreparedStatement pstmtPersonas = conn.prepareStatement(ELIMINAR_PERSONA)){
			
			//Eliminar de la tabla usuarios
			pstmtUsuarios.setInt(1, idUsuarios);
			pstmtUsuarios.executeUpdate();
			
			//Eliminar de la tabla personas
			pstmtPersonas.setInt(1, idPersonas);
			pstmtPersonas.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void actualizarUsuario(T_Personas tpersona, T_Usuarios tusuario) throws SQLException {
	    Connection conn = null;
	    PreparedStatement ps1 = null;
	    PreparedStatement ps2 = null;
	    
	    
	    try {
	        conn = Conexion.getConnection();
	        conn.setAutoCommit(false);  // Iniciar transacción

	        // Preparar las sentencias
	        ps1 = conn.prepareStatement(ACTUALIZAR_PERSONA);
	        ps2 = conn.prepareStatement(ACTUALIZAR_USUARIO);

	        // Configurar los parámetros para la tabla `personas`
	        System.out.println("INICIO DE EJECUCION DAO	");
	        System.out.println("Datos de entrada para `personas`: " + tpersona);
	        ps1.setInt(1, tpersona.getTipoDocumento());
	        ps1.setInt(2, tpersona.getNumeroIdentificacion());
	        ps1.setString(3, tpersona.getPrimerNombre());
	        ps1.setString(4, tpersona.getSegundoNombre());
	        ps1.setString(5, tpersona.getPrimerApellido());
	        ps1.setString(6, tpersona.getSegundoApellido());
	        ps1.setString(7, tpersona.getTelefono());
	        ps1.setString(8, tpersona.getDireccion());
	        ps1.setInt(9, tpersona.getGenero());
	        ps1.setInt(10, tpersona.getIdPersonas());
	        int filasAfectadas1 = ps1.executeUpdate();
	        
	        // Configurar los parámetros para la tabla `usuarios`
	        System.out.println("Datos de entrada para `usuarios`: " + tusuario);
	        ps2.setString(1, tusuario.getUsername());
	        ps2.setString(2, tusuario.getContraseña());
	        ps2.setString(3, tusuario.getCorreo());
	        ps2.setInt(4, tusuario.getEstadoUsuario());
	        ps2.setInt(5, tusuario.getRol());
	        ps2.setInt(6, tusuario.getIdUsuarios());
	        int filasAfectadas2 = ps2.executeUpdate();

	        // Confirmar la transacción
	        conn.commit();
	        
	        System.out.println("Actualización exitosa en ambas tablas");
	        System.out.println("Filas afectadas en `personas`: " + filasAfectadas1);
	        System.out.println("ID Usuario para actualización: " + tpersona.getIdPersonas());
	        System.out.println("Filas afectadas en `usuarios`: " + filasAfectadas2);
	        System.out.println("ID Usuario para actualización: " + tusuario.getIdUsuarios());
	        System.out.println("SQL: " + ACTUALIZAR_USUARIO);
	        System.out.println("Parámetros: " + tusuario.getUsername() + ", " + tusuario.getContraseña() + ", " + tusuario.getCorreo() + ", " + 				tusuario.getEstadoUsuario() + ", " + tusuario.getRol() + ", " + tusuario.getIdUsuarios());

	        
	    } catch (SQLException e) {
	        if (conn != null) {
	            try {
	                conn.rollback();  // Deshacer cambios en caso de error
	            } catch (SQLException rollbackEx) {
	                rollbackEx.printStackTrace();
	            }
	        }
	        e.printStackTrace();
	        throw new SQLException("Error al actualizar el usuario", e.getMessage());
	    } finally {
	        if (ps1 != null) {
	            try {
	                ps1.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (ps2 != null) {
	            try {
	                ps2.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.setAutoCommit(true);  // Restaurar autocommit
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	
	public List<ListarUsuarios> listAllUsers() throws SQLException {
	    List<ListarUsuarios> userList = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = Conexion.getConnection();
	        pstmt = conn.prepareStatement(LISTAR_USUARIOS);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        	int idUsuarios = rs.getInt("id_usuarios");
	            String tipoDocumento = rs.getString("tipo_documentos");
	            int numeroIdentificacion = rs.getInt("numeroIdentificacion");
	            String rol = rs.getString("roles");
	            String estadoUsuario = rs.getString("estadoUsuario");
	            int idPersonas = rs.getInt("id_personas");
	            String username = rs.getString("username");

	            ListarUsuarios user = new ListarUsuarios(idUsuarios, tipoDocumento, numeroIdentificacion, rol, estadoUsuario, idPersonas, username);
	            userList.add(user);
	            
	            // Agregar impresión para depuración
	            System.out.println("User: " + idUsuarios + ", " + tipoDocumento + ", " + numeroIdentificacion + ", " + rol + ", " + estadoUsuario + ", " + idPersonas);
	        }
	    } catch (SQLException e) {
	        System.err.println("SQL Exception: " + e.getMessage());
	        e.printStackTrace();
	        throw e;
	    } finally {
	        // Cerrar recursos en el orden inverso al de apertura
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar ResultSet: " + e.getMessage());
	                e.printStackTrace();
	            }
	        }
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar PreparedStatement: " + e.getMessage());
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                System.err.println("Error al cerrar Connection: " + e.getMessage());
	                e.printStackTrace();
	            }
	        }
	    }
	    return userList;
	}


	
	public void insertarUsuario(T_Personas tpersonas, T_Usuarios tusuarios) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmtPersona = null;
        PreparedStatement pstmtUsuario = null;
        
        try {
        	//Obtener conexion
        	conn = Conexion.getConnection();
        	
        	// Desactivar el modo de confirmación automática
        	conn.setAutoCommit(false);
        	
        	//Preparcion de la sentencia para insertar en la tabla 'personas'
        	pstmtPersona = conn.prepareStatement(INSERTAR_PERSONA, PreparedStatement.RETURN_GENERATED_KEYS);
        	pstmtPersona.setInt(1, tpersonas.getTipoDocumento());
        	pstmtPersona.setInt(2, tpersonas.getNumeroIdentificacion());
        	pstmtPersona.setString(3, tpersonas.getPrimerNombre());
        	pstmtPersona.setString(4, tpersonas.getSegundoNombre());
        	pstmtPersona.setString(5, tpersonas.getPrimerApellido());
        	pstmtPersona.setString(6, tpersonas.getSegundoApellido());
        	pstmtPersona.setString(7, tpersonas.getTelefono());
        	pstmtPersona.setString(8, tpersonas.getDireccion());
        	pstmtPersona.setInt(9, tpersonas.getGenero());
        	
        	//Ejecutar la inserccion en la tabla persona
        	pstmtPersona.executeUpdate();
        	
        	//Obtener el ID generado automáticamente para la tabla `personas`
        	var rs = pstmtPersona.getGeneratedKeys();
        	int personaId = 0;
        	if (rs.next()) {
				personaId = rs.getInt(1);
			}
        	
        	//Preparar la sentencia para insertar en la tabla `usuarios`
        	pstmtUsuario = conn.prepareStatement(INSERTAR_USUARIO);
        	pstmtUsuario.setString(1, tusuarios.getUsername());
        	pstmtUsuario.setString(2, tusuarios.getContraseña());
        	pstmtUsuario.setString(3, tusuarios.getCorreo());
        	pstmtUsuario.setInt(4, tusuarios.getEstadoUsuario());
        	pstmtUsuario.setInt(5, tusuarios.getRol());
        	pstmtUsuario.setInt(6, personaId); //Referencia al ID de Persona
        	
        	//Ejecutar la inserción en la tabla `usuarios`
        	pstmtUsuario.executeUpdate();
        	
        	//confirmar la transaccion
        	conn.commit();
        	System.out.println("Datos insertados exitosamente en ambas tablas.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
			try {
				if (conn != null) {
					conn.rollback(); //Revertir los cambios si hay un error
					System.out.println("Transacción revertida.");
				}
			} catch ( SQLException rollbackEx) {
				rollbackEx.printStackTrace();
				System.out.println(rollbackEx.getMessage());
			} finally {
				//cerrar recursos
				try {
					if (pstmtPersona != null) pstmtPersona.close();
					if (pstmtUsuario != null) pstmtUsuario.close();
					if (conn != null) conn.setAutoCommit(true); // Reactivar el autocommit
				} catch (SQLException closeEx) {
					closeEx.printStackTrace();
					System.out.println(closeEx.getMessage());
				}
			}
		}
	}
	
	public List<ListarUsuarios> findUsersByNumeroIdentificacion(int numeroIdentificacion) throws SQLException {
	    List<ListarUsuarios> userList = new ArrayList<>();
	    try (Connection conn = Conexion.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(LISTAR_USUARIO)) {
	        stmt.setInt(1, numeroIdentificacion);  // Asegúrate de usar el valor de número de identificación
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	            	int idUsuarios = rs.getInt("id_usuarios");
	                String tipoDocumento = rs.getString("tipo_documentos");
	                int numIdentificacion = rs.getInt("numeroIdentificacion");
	                String rol = rs.getString("roles");
	                String estadoUsuario = rs.getString("estadoUsuario");
	                int idPersonas = rs.getInt("id_personas");
	                String username = rs.getString("username");

	                ListarUsuarios user = new ListarUsuarios(idUsuarios, tipoDocumento, numIdentificacion, rol, estadoUsuario, idPersonas, username);
	                userList.add(user);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("SQL Exception: " + e.getMessage());
	        e.printStackTrace();
	        throw e;
	    }
	    return userList;
	}

}