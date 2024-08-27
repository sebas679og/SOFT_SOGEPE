package com.sagmade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sagmade.config.Conexion;
import com.sagmade.model.T_Personas;
import com.sagmade.model.T_Usuarios;

public class ModuloUsuarios {

	public ModuloUsuarios() {
		super();
	}
	
	private static final String INSERTAR_PERSONA = ("INSERT INTO personas (tipoDocumento, numeroIdentificacion, primerNombre, segundoNombre, primerApellido, 			segundoApellido, telefono, direccion, genero) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	
	private static final String INSERTAR_USUARIO = ("INSERT INTO usuarios (username, contraseña, correo, estadoUsuario, rol, persona) "
			+ "VALUES ( ?, ?, ?, ?, ?, ?)");
	
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
