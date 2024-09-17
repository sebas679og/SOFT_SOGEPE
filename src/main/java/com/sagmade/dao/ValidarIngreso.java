package com.sagmade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sagmade.config.Conexion;

public class ValidarIngreso {
	private Connection connection;
    
    public ValidarIngreso() throws SQLException {
    	this.connection = Conexion.getConnection();
    }
    
    public boolean validarUsuario(String username, String contraseña, String tipoUsuario) {
	    String sql = "SELECT u.username, r.Rol "
	        + "FROM usuarios u "
	        + "INNER JOIN roles r ON u.rol = r.idRoles "
	        + "WHERE u.username = ? AND u.contraseña = ? AND r.Rol = ?";
	    
	      try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, username);
	            stmt.setString(2, contraseña);
	            stmt.setString(3, tipoUsuario);
	
	            try (ResultSet rs = stmt.executeQuery()) {
	                return rs.next(); // Si hay un registro, el usuario es válido
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false; // En caso de error, retornamos falso
	        }
    }

}
