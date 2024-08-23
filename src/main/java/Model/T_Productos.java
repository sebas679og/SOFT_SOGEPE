package Model;

public class T_Productos {
	protected int idProductos;
	protected String producto;
	protected double valorCompra;
	protected double valorVenta;
	protected int estadoDelObjeto;
	protected int composicion;
	
	public T_Productos() {
		super();
	}

	public T_Productos(String producto, double valorCompra, double valorVenta, int estadoDelObjeto, int composicion) {
		super();
		this.producto = producto;
		this.valorCompra = valorCompra;
		this.valorVenta = valorVenta;
		this.estadoDelObjeto = estadoDelObjeto;
		this.composicion = composicion;
	}

	public T_Productos(int idProductos, String producto, double valorCompra, double valorVenta, int estadoDelObjeto,
			int composicion) {
		super();
		this.idProductos = idProductos;
		this.producto = producto;
		this.valorCompra = valorCompra;
		this.valorVenta = valorVenta;
		this.estadoDelObjeto = estadoDelObjeto;
		this.composicion = composicion;
	}

	public int getIdProductos() {
		return idProductos;
	}

	public void setIdProductos(int idProductos) {
		this.idProductos = idProductos;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public double getValorVenta() {
		return valorVenta;
	}

	public void setValorVenta(double valorVenta) {
		this.valorVenta = valorVenta;
	}

	public int getEstadoDelObjeto() {
		return estadoDelObjeto;
	}

	public void setEstadoDelObjeto(int estadoDelObjeto) {
		this.estadoDelObjeto = estadoDelObjeto;
	}

	public int getComposicion() {
		return composicion;
	}

	public void setComposicion(int composicion) {
		this.composicion = composicion;
	}
	
	
}
