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
	
	private static final String INSERTAR_PERSONA = ("INSERT INTO personas (tipoDocumento, numeroIdentificacion, primerNombre, segundoNombre, primerApellido, 			segundoApellido, telefono, direccion, genero) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	
	private static final String INSERTAR_USUARIO = ("INSERT INTO usuarios (username, contraseña, correo, estadoUsuario, rol, persona) "
			+ "VALUES ( ?, ?, ?, ?, ?, ?)");
	
	private static final String LISTAR_USUARIOS = ("SELECT td.sigla AS tipo_documentos, p.numeroIdentificacion, r.rol AS roles, eu.estado AS estadoUsuario "
			+ "FROM personas p "
			+ "JOIN usuarios u ON p.idPersonas = u.persona "
			+ "JOIN roles r ON u.rol = r.idRoles "
			+ "JOIN tipo_documentos td ON p.tipoDocumento = td.idTipo_Documentos "
			+ "JOIN estado_usuarios eu ON u.estadoUsuario = eu.idEstado_Usuarios;");
	
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
	            String tipoDocumento = rs.getString("tipo_documentos");
	            int numeroIdentificacion = rs.getInt("numeroIdentificacion");
	            String rol = rs.getString("roles");
	            String estadoUsuario = rs.getString("estadoUsuario");

	            ListarUsuarios user = new ListarUsuarios(tipoDocumento, numeroIdentificacion, rol, estadoUsuario);
	            userList.add(user);
	            
	            // Agregar impresión para depuración
	            System.out.println("User: " + tipoDocumento + ", " + numeroIdentificacion + ", " + rol + ", " + estadoUsuario);
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
}