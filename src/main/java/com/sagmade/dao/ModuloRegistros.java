package com.sagmade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sagmade.config.Conexion;
import com.sagmade.model.T_Actividades;
import com.sagmade.model.T_Areas;
import com.sagmade.model.T_RegistroInformes;
import com.sagmade.model.T_Usuarios;


public class ModuloRegistros {

	public ModuloRegistros() {
		super();
	}
	
	private static final String LISTAR_ACTIVIDADES = ("SELECT idActividades, actividad FROM actividades WHERE area = ?");
	
	private static final String LISTAR_AREAS = ("SELECT * FROM areas");
	
	private static final String LISTAR_USUARIOS = ("SELECT idUSuarios, username FROM usuarios");
	
	private static final String INSERTAR_REGISTRO = ("INSERT INTO resgistro_informes (usuario, areaTrabajo, actividad, fechaRegistro, descripcion, observacion) "
			+ "VALUES (?, ?, ?, ?, ?, ?)");
	
	public void insertarRegistro(T_RegistroInformes tregistros) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = Conexion.getConnection();
			
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(INSERTAR_REGISTRO);
			ps.setInt(1, tregistros.getUsuario());
			ps.setInt(2, tregistros.getAreaTrabajo());
			ps.setInt(3, tregistros.getActividad());
			ps.setString(4, tregistros.getFechaRegistro());
			ps.setString(5, tregistros.getDescripcion());
			ps.setString(6, tregistros.getObservacion());
			ps.executeUpdate();
			
			conn.commit();
			System.out.println("Datos insertados exitosamente en tabla Registro Informes.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.setAutoCommit(true); // Reactivar el autocommit
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
				System.out.println(closeEx.getMessage());
			}
		}
	}
	
	public List<T_Usuarios> obtenerUsuarios(){
		List<T_Usuarios> usuarios = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		 try {
			 //Establecer Conexion BD
			conn = Conexion.getConnection();
			
			//ejecutar consulta SQL
			ps = conn.prepareStatement(LISTAR_USUARIOS);
			rs = ps.executeQuery();
			
			//Recorrer resultados y añadirlos a la lista
			while (rs.next()) {
				int idUsuarios = rs.getInt("idUsuarios");
				String username = rs.getString("username");
				usuarios.add(new T_Usuarios(idUsuarios, username));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
		return usuarios;
	}
	
	public List<T_Actividades> obtenerActividadesPorArea(int idArea){
		List<T_Actividades> actividades = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		 try {
			 //Establecer Conexion BD
			conn = Conexion.getConnection();
			
			//ejecutar consulta SQL
			ps = conn.prepareStatement(LISTAR_ACTIVIDADES);
			ps.setInt(1, idArea);
			rs = ps.executeQuery();
			
			//Recorrer resultados y añadirlos a la lista
			while (rs.next()) {
				int idActividades = rs.getInt("idActividades");
				String actividad = rs.getString("actividad");
				
				actividades.add(new T_Actividades(idActividades, actividad));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
		 return actividades;
	}
	
	public List<T_Areas> obtenerAreas(){
		List<T_Areas> areas = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			 //Establecer Conexion BD
			conn = Conexion.getConnection();
			
			//ejecutar consulta SQL
			ps = conn.prepareStatement(LISTAR_AREAS);
			rs = ps.executeQuery();
			
			//Recorrer resultados y añadirlos a la lista
			while (rs.next()) {
				int idArea = rs.getInt("idArea");
				String area = rs.getString("area");
				areas.add(new T_Areas(idArea, area));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
               if (rs != null) rs.close();
               if (ps != null) ps.close();
               if (conn != null) conn.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
		}
		return areas;
	}
}
