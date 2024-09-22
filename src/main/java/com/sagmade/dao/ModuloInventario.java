package com.sagmade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sagmade.config.Conexion;
import com.sagmade.model.ListarInventario;
import com.sagmade.model.ListarProductos;
import com.sagmade.model.ListarUsuarios;
import com.sagmade.model.T_Categorias;
import com.sagmade.model.T_Inventario;
import com.sagmade.model.T_Productos;

public class ModuloInventario {
	public ModuloInventario() {
		super();
	}
	
	private static final String CODIGO_PRODUCTO = ("SELECT COUNT(*) FROM productos WHERE codigo = ?");
	
	private static final String INSERTAR_PRODUCTO = ("INSERT INTO productos (codigo, producto, categoria) VALUES (?, ?, ?)");
	
	private static final String LISTAR_CATEGORIAS = ("SELECT * FROM categorias");
	
	private static final String LISTAR_PRODUCTOS = ("SELECT idProductos, producto FROM productos");
	
	private static final String INSERTAR_INVENTARIO = ("INSERT INTO inventario (fechaIngreso, cantidad, producto) VALUES (?, ?, ?)");
	
	private static final String LISTAR_PRODUCTS = ("SELECT p.codigo AS codigo, p.producto AS producto, c.categoria AS categoria "
			+ "FROM productos p JOIN categorias c ON p.categoria = c.idCategoria");
	
	private static final String LISTAR_PRODUCT = ("SELECT p.codigo AS codigo, p.producto AS producto, c.categoria AS categoria "
			+ "FROM productos p JOIN categorias c ON p.categoria = c.idCategoria "
			+ "WHERE p.codigo = ?");
	
	private static final String LISTAR_INVENTARIO = ("SELECT i.idInventario AS id_inventario, i.fechaIngreso AS fecha_ingreso, c.categoria AS categoria, "
			+ "p.producto AS producto, i.cantidad AS cantidad "
			+ "FROM inventario i "
			+ "JOIN productos p ON i.producto = p.idProductos "
			+ "JOIN Categorias c ON p.categoria = c.idCategoria");
	
	private static final String LISTAR_INVENT = ("SELECT i.idInventario AS id_inventario, i.fechaIngreso AS fecha_ingreso, c.categoria AS categoria, "
			+ "p.producto AS producto, i.cantidad AS cantidad "
			+ "FROM inventario i "
			+ "JOIN productos p ON i.producto = p.idProductos "
			+ "JOIN Categorias c ON p.categoria = c.idCategoria "
			+ "WHERE i.idInventario = ?");
	
	private static final String ELIMINAR_PRODUCTO = ("DELETE FROM productos WHERE codigo = ?");
	
	private static final String ELIMINAR_INVENTARIO = ("DELETE FROM inventario WHERE idInventario = ?");
	
	private static final String ACTUALIZAR_INVENTARIO = ("UPDATE inventario SET fechaIngreso = ?, cantidad = ?, producto = ? WHERE idInventario = ?;");
	
	public void actualizarInventario(T_Inventario tinventario) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = Conexion.getConnection();
			
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(ACTUALIZAR_INVENTARIO);
			ps.setString(1, tinventario.getFechaIngreso());
			ps.setInt(2, tinventario.getCantidad());
			ps.setInt(3, tinventario.getProducto());
			ps.setInt(4, tinventario.getIdInventario());
			ps.executeUpdate();
			
			conn.commit();
			System.out.println("Datos actualizados exitosamente en tabla Inventario.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.setAutoCommit(true); // Reactivar el autocommit
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
				System.out.println(closeEx.getMessage());
			}
			
		}
	}
	
	public void eliminarInventario(int idInventario) throws SQLException{
		try (Connection conn = Conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(ELIMINAR_INVENTARIO)){
			
			//Eliminar de la tabla usuarios
			ps.setInt(1, idInventario);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void eliminarProducto(int codigo) throws SQLException{
		try (Connection conn = Conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(ELIMINAR_PRODUCTO)){
			
			//Eliminar de la tabla usuarios
			ps.setInt(1, codigo);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void insertarInventario(T_Inventario tinventario) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = Conexion.getConnection();
			
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(INSERTAR_INVENTARIO);
			ps.setString(1, tinventario.getFechaIngreso());
			ps.setInt(2, tinventario.getCantidad());
			ps.setInt(3, tinventario.getProducto());
			ps.executeUpdate();
			
			conn.commit();
			System.out.println("Datos insertados exitosamente en tabla Inventario.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			
			try {
				if (ps != null) ps.close();
				if (conn != null) conn.setAutoCommit(true); // Reactivar el autocommit
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
				System.out.println(closeEx.getMessage());
			}
			
		}
	}
	
	public List<T_Productos> obtenerProductos(){
		List<T_Productos> productos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			 //Establecer Conexion BD
			conn = Conexion.getConnection();
			
			//ejecutar consulta SQL
			ps = conn.prepareStatement(LISTAR_PRODUCTOS);
			rs = ps.executeQuery();
			
			//Recorrer resultados y añadirlos a la lista
			while (rs.next()) {
				int idProductos = rs.getInt("idProductos");
				String producto = rs.getString("producto");
				productos.add(new T_Productos(idProductos, producto));
				
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
		 return productos;
	}
	
	public List<T_Categorias> obtenerCategorias(){
		List<T_Categorias> categorias = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		 try {
			 //Establecer Conexion BD
			conn = Conexion.getConnection();
			
			//ejecutar consulta SQL
			ps = conn.prepareStatement(LISTAR_CATEGORIAS);
			rs = ps.executeQuery();
			
			//Recorrer resultados y añadirlos a la lista
			while (rs.next()) {
				int idCategoria = rs.getInt("idCategoria");
				String categoria = rs.getString("categoria");
				categorias.add(new T_Categorias(idCategoria, categoria));
				
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
		 return categorias;
	}
	
	public void insertarProducto(T_Productos tproductos) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmtCheck = null;
	    PreparedStatement pstmtInsertar = null;
	    ResultSet rs = null;

	    try {
	        // Obtener conexión
	        conn = Conexion.getConnection();
	        conn.setAutoCommit(false); // Desactivar modo de confirmación automática

	        // Preparar sentencia para verificar existencia del código
	        pstmtCheck = conn.prepareStatement(CODIGO_PRODUCTO);
	        pstmtCheck.setInt(1, tproductos.getCodigo());
	        rs = pstmtCheck.executeQuery();
	        
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            if (count > 0) {
	                // Si el producto ya existe, notificar al usuario
	                System.out.println("El producto con el código " + tproductos.getCodigo() + " ya existe.");
	                return; // Salir del método
	            }
	        }
	        
	        // Preparación de la sentencia para insertar en la tabla 'productos'
	        pstmtInsertar = conn.prepareStatement(INSERTAR_PRODUCTO);
	        pstmtInsertar.setInt(1, tproductos.getCodigo());
	        pstmtInsertar.setString(2, tproductos.getProducto());
	        pstmtInsertar.setInt(3, tproductos.getCategoria());
	        
	        // Ejecución de la inserción en la tabla 'productos'
	        pstmtInsertar.executeUpdate();
	        
	        // Confirmar la transacción
	        conn.commit();
	        System.out.println("Producto Insertado exitosamente");

	    } catch (SQLException e) {
	        if (conn != null) {
	            try {
	                conn.rollback(); // Revertir cambios en caso de error
	            } catch (SQLException rollbackEx) {
	                rollbackEx.printStackTrace();
	            }
	        }
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    } finally {
	        // Cerrar recursos
	        try {
	            if (rs != null) rs.close();
	            if (pstmtCheck != null) pstmtCheck.close();
	            if (pstmtInsertar != null) pstmtInsertar.close();
	            if (conn != null) conn.setAutoCommit(true); // Reactivar autocommit
	        } catch (SQLException closeEx) {
	            closeEx.printStackTrace();
	            System.out.println(closeEx.getMessage());
	        }
	    }
	}
	
	public List<ListarProductos> findProductsByNumeroCodigoP(int codigoProducto) throws SQLException {
	    List<ListarProductos> productstList = new ArrayList<>();
	    try (Connection conn = Conexion.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(LISTAR_PRODUCT)) {
	        stmt.setInt(1, codigoProducto);  // Asegúrate de usar el valor de número de identificación
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	            	int codigo = rs.getInt("codigo");
	                String producto = rs.getString("producto");
	                String categoria = rs.getString("categoria");

	                ListarProductos product = new ListarProductos(codigo, producto, categoria);
	                productstList.add(product);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("SQL Exception: " + e.getMessage());
	        e.printStackTrace();
	        throw e;
	    }
	    return productstList;
	}
	
	public List<ListarInventario> findInventsByNumeroCodigoI(int codigoInventario) throws SQLException {
	    List<ListarInventario> inventList = new ArrayList<>();
	    try (Connection conn = Conexion.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(LISTAR_INVENT)) {
	        stmt.setInt(1, codigoInventario);  // Asegúrate de usar el valor de número de identificación
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	            	int idInventario = rs.getInt("id_inventario");
	                String fechaIngreso = rs.getString("fecha_ingreso");
	                String categoria = rs.getString("categoria");
	                String producto = rs.getString("producto");
	                int cantidad = rs.getInt("cantidad");

	                ListarInventario invent = new ListarInventario(idInventario, fechaIngreso, categoria, producto, cantidad);
	                inventList.add(invent);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("SQL Exception: " + e.getMessage());
	        e.printStackTrace();
	        throw e;
	    }
	    return inventList;
	}
	
	public List<ListarInventario> listAllInventario() throws SQLException {
	    List<ListarInventario> inventList = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = Conexion.getConnection();
	        pstmt = conn.prepareStatement(LISTAR_INVENTARIO);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        	int idInventario = rs.getInt("id_inventario");
                String fechaIngreso = rs.getString("fecha_ingreso");
                String categoria = rs.getString("categoria");
                String producto = rs.getString("producto");
                int cantidad = rs.getInt("cantidad");

                ListarInventario invent = new ListarInventario(idInventario, fechaIngreso, categoria, producto, cantidad);
                inventList.add(invent);
	            
	            // Agregar impresión para depuración
	            System.out.println("Inventario: " + idInventario + ", " + fechaIngreso + ", " + categoria + ", " + producto + ", "
	            + cantidad);
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
	    return inventList;
	}
	
	public List<ListarProductos> listAllProducts() throws SQLException {
	    List<ListarProductos> productstList = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = Conexion.getConnection();
	        pstmt = conn.prepareStatement(LISTAR_PRODUCTS);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        	int codigo = rs.getInt("codigo");
                String producto = rs.getString("producto");
                String categoria = rs.getString("categoria");

                ListarProductos product = new ListarProductos(codigo, producto, categoria);
                productstList.add(product);
	            
	            // Agregar impresión para depuración
	            System.out.println("Producto: " + codigo + ", " + producto + ", " + categoria);
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
	    return productstList;
	}

}
