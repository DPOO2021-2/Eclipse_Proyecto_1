package procesamiento;

import java.util.HashMap;

import modelo.Producto;

public class Inventario 
{
	private HashMap<String, Producto> productos;
	
	public Inventario()
	{
		this.productos = new HashMap<String, Producto>();
	}
	
	
	public void getCostoProducto(String codigoBarras) 
	{
		
	}
	
	public void comprar(String codigoBarras, double cantidad) 
	{
		// SEGUIR AQUI
	}
	
	public void registrarLote(String codigoBarras, int cantidadOriginal, int cantidadActual, String fecha_vencimiento,
			int costoTotal, int precio_publico_unidad, int precio_unidad_medida, String nombre,
			String categoria, String subcategoria1, String subcategoria2, String tipoRefrigerado, int precioActual) 
	{
		
	}
	
	public void registrarProducto(int codigoBarras, int cantidadOriginal, int cantidadActual, String fecha_vencimiento,
			int costoTotal, int precio_publico_unidad, int precio_unidad_medida) 
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
