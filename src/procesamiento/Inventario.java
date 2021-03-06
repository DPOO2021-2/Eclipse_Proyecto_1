package procesamiento;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


import modelo.Lote;
import modelo.Producto;
import modelo.ProductoKilos;
import modelo.Producto;
import modelo.ProductoUnidades;
import modelo.TransformadorFechas;

import java.util.Date;

public class Inventario 
{
	
	//llave, valor = string codigoBarras, objeto producto
	private HashMap<String, Producto> productos;
	
	public Inventario()
	{
		this.productos = new HashMap<String, Producto>();
	}
	
	public void guardarProducto(String codigoBarras, Producto producto)
	{
		productos.put(codigoBarras, producto);
	}
	
	
	public double getCostoProducto(String codigoBarras) throws NullPointerException
	{
		try 
		{
		Producto producto = getProducto(codigoBarras);
		double costoTotalProducto = producto.getCostoTotal();
		return costoTotalProducto;
		}
		catch(NullPointerException ne)
		{
			throw ne;
		}
	}
	
	public double getGananciaProducto(String codigoBarras) throws NullPointerException
	{
		
		Producto producto = getProducto(codigoBarras);
		try 
		{
			double gananciaTotalProducto = producto.getGananciaTotal();
			return gananciaTotalProducto;
		}
		
		catch(NullPointerException ne)
		{
			throw ne;
		}
		
		
		
	}
	
	
	



	
	public double comprar(String codigoBarras, double cantidad) 
	{
		
		Producto producto = getProducto(codigoBarras);
		double precioProd = producto.comprarProducto(cantidad);
		return precioProd;
	}
	
	public Producto registrarProducto(String codigoBarras, double precio_publico_unidad, String precio_publico_unidad_medida, String nombre,
			String[] categorias, String tipoRefrigerado, String empaque) 
	 
	{
		
		
		Producto producto = null;
		
		if (productoRegistrado(codigoBarras))
		{
			producto = getProducto(codigoBarras);
			producto.actualizarPrecio(precio_publico_unidad, precio_publico_unidad_medida);
			
		}
		else
		{
			//System.out.println(empaque);
			if (empaque.toLowerCase().equals("kilos"))
			{
				producto = new ProductoKilos(nombre, codigoBarras, tipoRefrigerado, precio_publico_unidad, precio_publico_unidad_medida);
				
			}
			else if(empaque.toLowerCase().equals("unidades"))
			{
				producto = new ProductoUnidades(nombre, codigoBarras, tipoRefrigerado, precio_publico_unidad, precio_publico_unidad_medida);
				
			}
			guardarProducto(codigoBarras, producto);
		}
		if(!(categorias==null)) 
		{
			producto.agregarCategorias(categorias);
		}
		
		return producto;
		
	}
	
	public void registrarLote(Producto producto, String codigoLote, double cantidadOriginal, Date fechaVencimiento, double costoTotal,
			double precio_publico_unidad, String precio_publico_unidad_medida)
	{
		Lote lote = new Lote(codigoLote, cantidadOriginal, fechaVencimiento, costoTotal, precio_publico_unidad, precio_publico_unidad_medida);
		producto.agregarLote(lote);
		
	}
	
	public void registrarLote2(Producto producto, String codigoLote, double cantidadOriginal, Date fechaVencimiento, double costoTotal,
			double precio_publico_unidad, String precio_publico_unidad_medida)
	{
		Lote lote = new Lote(codigoLote, cantidadOriginal, fechaVencimiento, costoTotal, precio_publico_unidad, precio_publico_unidad_medida);
		producto.agregarLote2(lote);
		
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
//				System.out.println(TransformadorFechas.fechaTransf(fecha_vencimiento).equals(partes[1]));
				double costoTotal = Double.parseDouble(partes[2]);
				double precio_publico_unidad = Double.parseDouble(partes[3]);
				String precio_publico_unidad_medida = partes[4];
				String nombre = partes[5];
				String[] categorias = partes[7].split("-");
				String tipoRefrigerado = partes[8];
				String empaque = partes[9];
				
				//System.out.println("estoy leyendo linea de csv");
				
				String[] codigos =  codigoBarras_lote.split("-");
				String codigoBarras = codigos[0];
				String codigoLote = codigos[1];
				
				
				//aqui debo llamar al metodo registrarProducto( con toda la info de la linea)
				Producto producto = registrarProducto(codigoBarras, precio_publico_unidad, precio_publico_unidad_medida, nombre,
					 categorias, tipoRefrigerado, empaque);
				registrarLote(producto, codigoLote, cantidadOriginal, fecha_vencimiento, costoTotal,
						precio_publico_unidad, precio_publico_unidad_medida);
				
				
				
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
		if(producto==null)
		{
			return false;
		}
		else 
		{
			return producto.eliminarLoteVencido(codigoLote);
		}
		
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

	public Lote getLote(String codigoBarras, String codigoLote) 
	{
		Producto producto = getProducto(codigoBarras);
		
		return producto.getLote(codigoLote);
	}

	
	
	public void agregarCategoriasProducto(String codigoBarras, String[] categorias) 
	{
		Producto producto = getProducto(codigoBarras);
		producto.agregarCategorias(categorias);
		
	}

	public Map<String, Producto> getProductos() {
		// TODO Auto-generated method stub
		return this.productos;
	}

	public boolean vacio() 
	{
		return this.productos.isEmpty();
	}

	public Map<String, Double> lotesYcantidades(String codigoBarras) throws NullPointerException
	{
		
		try
		{
			Producto producto = getProducto(codigoBarras);
			LinkedHashMap<String, Double> resultado = new LinkedHashMap<String, Double>();
			LinkedHashMap<String, Lote> lotes = producto.getLotes();
			for (String codigoLote: lotes.keySet())
			{
				resultado.put(codigoLote, lotes.get(codigoLote).getCantidadActual());
			}
			return resultado;
		}
		catch(NullPointerException ne)
		{
			throw ne;
		}
	}
	






	


	
}
