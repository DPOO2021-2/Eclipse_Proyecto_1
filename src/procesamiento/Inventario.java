package procesamiento;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


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
	
	public void guardarProducto(String codigoBarras, Producto producto)
	{
		productos.put(codigoBarras, producto);
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
			if (empaque.toLowerCase().equals("kilos"))
			{
				producto = new ProductoKilos(nombre, codigoBarras, tipoRefrigerado, precio_publico_unidad);
				
			}
			else if(empaque.toLowerCase().equals("unidades"))
			{
				producto = new ProductoUnidades(nombre, codigoBarras, tipoRefrigerado, precio_publico_unidad);
				
			}
			guardarProducto(codigoBarras, producto);
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
	
	public void leerArchivoLotes(String nombreArchivo) throws IOException, ParseException
	{try 
	{
			
			//leer csv y configurar lectura para leer atributos:
			//cada linea debe llamar a registrarProducto()
			BufferedReader br = new BufferedReader(new FileReader("datos/"+nombreArchivo+".csv"));
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
			
			String linea = br.readLine();
			linea = br.readLine();
			while (linea != null) 
			{	
				
				//System.out.println(linea);
				String[] partes = linea.split(",");
				
				String codigoBarras_lote = partes[6];
				double cantidadOriginal = Double.parseDouble(partes[0]);
				Date fecha_vencimiento = formatter.parse(partes[1]);
				double costoTotal = Double.parseDouble(partes[2]);
				double precio_publico_unidad = Double.parseDouble(partes[3]);
				String precio_publico_unidad_medida = partes[4];
				String nombre = partes[5];
				String categoria = partes[7];
				String tipoRefrigerado = partes[8];
				String empaque = partes[9];
				
				//System.out.println("estoy leyendo linea de csv");
				
				
				//aqui debo llamar al metodo registrarProducto( con toda la info de la linea)
				registrarProducto(codigoBarras_lote, cantidadOriginal, fecha_vencimiento,
					 costoTotal, precio_publico_unidad, precio_publico_unidad_medida, nombre,
					 categoria, tipoRefrigerado, empaque);
				
				
				
				linea = br.readLine();
				
			}
			br.close();
	}	
	catch(FileNotFoundException ex)
	{
		System.out.println("Archivo no encontrado");
	}
	}
	
	public boolean eliminarLote(String codigoBarras, String codigoLote) 
	{
		Producto producto = getProducto(codigoBarras);
		return producto.eliminarLoteVencido(codigoLote);
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


	


	
}
