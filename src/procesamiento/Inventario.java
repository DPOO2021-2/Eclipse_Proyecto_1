package procesamiento;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


import modelo.Lote;
import modelo.Producto;
import modelo.ProductoKilos;
import modelo.Producto;
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
	
	public void leerArchivoLotes(String nombreArchivo) throws FileNotFoundException, IOException
	{try 
	{
			boolean seCargoCorrectamente = false;
			//leer csv y configurar lectura para leer atributos:
			//cada linea debe llamar a registrarProducto()
			BufferedReader br = new BufferedReader(new FileReader("datos/"+nombreArchivo+".xlsx"));
			
			String linea = br.readLine();
			while (linea != null) 
			{
				
				String[] partes = linea.split(";");
				
				//System.out.println("estoy leyendo linea de csv");
				
				
				//aqui debo llamar al metodo registrarProducto( con toda la info de la linea)
				
				
				
	
				linea = br.readLine();
			}
			br.close();
	}	
	catch(FileNotFoundException ex)
	{
		System.out.println("Archivo no encontrado");
	}
	}
	
	public void eliminarLote(String codigoBarras, String fecha) 
	{
		
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
