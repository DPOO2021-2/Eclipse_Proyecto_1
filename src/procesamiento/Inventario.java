package procesamiento;

import java.util.HashMap;

import modelo.Lote;
import modelo.Producto;

public class Inventario 
{
	private HashMap<String, Producto> productos;
	
	public Inventario()
	{
		this.productos = new HashMap<String, Producto>();
	}
	
	
	public double getCostoProducto(String codigoBarras) 
	{
		Producto producto = getProducto(codigoBarras);
		double costoTotalProducto = producto.getCostoTotal();
		return costoTotalProducto;
		
	}
	
	public double getGananciaProducto(String codigoBarras) 
	{
		Producto producto = getProducto(codigoBarras);
		double gananciaTotalProducto = producto.getGananciaTotal();
		return gananciaTotalProducto;
		
	}
	
	
	



	
	public double comprar(String codigoBarras, double cantidad) 
	{
		
		Producto producto = getProducto(codigoBarras);
		double precioProd = producto.comprarProducto(cantidad);
		return precioProd;
	}
	
	public void registrarLote(String codigoBarras, double cantidadOriginal, String fecha_vencimiento,
			double costoTotal, double precio_publico_unidad, double precio_unidad_medida, String nombre,
			String categoria, String tipoRefrigerado) 
	{
		
	}
	
	public void registrarProducto(String codigoBarras, double cantidadOriginal, String fecha_vencimiento,
			double costoTotal, int precio_publico_unidad, int precio_unidad_medida) 
	{
		
	}
	
	public void eliminarLote(String codigoBarras, String fecha) 
	{
		
	}
	
	

	public boolean sePuedeComprar(String codigoBarras, double cantidad) 
	{
		Producto producto = getProducto(codigoBarras);
		
		return producto.sePuedeComprar(cantidad);
	}

	
	
	
	
	private Producto getProducto(String codigoBarras) 
	{
		return productos.get(codigoBarras);
	}


	public void crearArchivos() 
	{
		
	}
	
	public void actualizarArchivos() 
	{
		
	}
}
