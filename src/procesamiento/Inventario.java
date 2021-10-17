package procesamiento;

import java.util.HashMap;

import modelo.Lote;
import modelo.Producto;
import modelo.ProductoKilos;
import modelo.ProductoUnidades;

import java.util.Date;

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
	
	public void registrarProducto(String codigoBarras_lote, double cantidadOriginal, Date fecha_vencimiento,
			double costoTotal, double precio_publico_unidad, String precio_publico_unidad_medida, String nombre,
			String categoria, String tipoRefrigerado, String empaque) 
	
	{
		String[] codigos =  codigoBarras_lote.split("-");
		String codigoBarras = codigos[0];
		String codigoLote = codigos[1];
		
		Producto producto = null;
		
		if (productoRegistrado(codigoBarras))
		{
			producto = getProducto(codigoBarras);
			
		}
		else
		{
			if (empaque.equals("kilos"))
			{
				producto = new ProductoKilos(nombre, codigoBarras, tipoRefrigerado, precio_publico_unidad);
			}
			else if(empaque.equals("unidades"))
			{
				producto = new ProductoUnidades(nombre, codigoBarras, tipoRefrigerado, precio_publico_unidad);
			}
		}
		
		producto.agregarCategoria(categoria);
		registrarLote(producto, codigoLote, cantidadOriginal, fecha_vencimiento, costoTotal, precio_publico_unidad, precio_publico_unidad_medida);
		
	}
	
	public void registrarLote(Producto producto, String codigoLote, double cantidadOriginal, Date fechaVencimiento, double costoTotal,
			double precio_publico_unidad, String precio_publico_unidad_medida)
	{
		Lote lote = new Lote(codigoLote, cantidadOriginal, fechaVencimiento, costoTotal, precio_publico_unidad, precio_publico_unidad_medida);
		producto.agregarLote(lote);
	}
	
	public void leerArchivoLotes(String nombreArchivo) 
	{
		//leer csv y configurar lectura para leer atributos:
		//cada linea debe llamar a registrarProducto()
		
	}
	
	public boolean eliminarLote(String codigoBarras, String codigoLote) 
	{
		Producto producto = getProducto(codigoBarras);
		return producto.eliminarLote(codigoLote);
	}
	
	

	public boolean sePuedeComprar(String codigoBarras, double cantidad) 
	{
		if (productoRegistrado(codigoBarras))
		{
			Producto producto = getProducto(codigoBarras);
			
			return producto.sePuedeComprar(cantidad);
		}
		else
		{
			return false;
		}
	}

	
	
	public boolean productoRegistrado(String codigoBarras)
	{
		return productos.containsKey(codigoBarras);
	}
	
	
	public Producto getProducto(String codigoBarras) 
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
