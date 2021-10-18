package procesamiento;

import java.util.Map;

import modelo.Cliente;
import modelo.Compra;
import modelo.Lote;
import modelo.Producto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Supermercado 
{
	private Inventario inventario;
	private RegistroCompras registroCompras;
	private SistemaPuntos sistemaPuntos;
	
	private String nombre;
	private Date fecha;
	
	public Supermercado (String nombre) throws IOException, ParseException
	{
		
		/*nos falta:
		 * 
		 * 1. req UsuarioInventario: input:producto(codigoBarras) Output: los codigos lotes que hay con sus cantidades actuales
		 * 
		 *  
		 * 2.  consola inventario
		 * 
		 * 3. actualizar diagramas
		 * 
		 * 4.  Hacer archivos: guardar: todos, leer: productos
		 * 
		 * 5. organizar entrega
		 * 
		 */
		this.nombre = nombre;
		this.fecha = new Date();
		
		this.inventario = new Inventario();
		this.registroCompras = new RegistroCompras();
		this.sistemaPuntos = new SistemaPuntos();
		
		//aqui se debe ejecutar el metodo de cargar nuestros archivos
		
		leerInfoArchivos("inventario_productos");
		leerInfoArchivos("inventario_lotes");
		leerInfoArchivos("puntos");
		
	}
	
	public Inventario getInventario()
	{
		return this.inventario;
	}
	
	public SistemaPuntos getSistemaPuntos()
	{
		return this.sistemaPuntos;
	}
	
	public RegistroCompras getRegistroCompras() 
	{
		
		return this.registroCompras;
	}
	
	public boolean registrarCliente(String nombre, String cedula, String sexo, int edad, String estadoCivil, String situacionLaboral)
	{
		return getSistemaPuntos().registrarCliente(nombre, cedula, sexo, edad, estadoCivil, situacionLaboral);
		//retorna false si el cliente ya estaba registrado (no lo registra de nuevo)
	}
	
	
	public boolean registrarLotes(String nombreArchivo) throws FileNotFoundException, IOException, ParseException
	{
		Inventario inventario = getInventario();
		if (existeArchivoUsuarioLote(nombreArchivo))
		{
			inventario.leerArchivoLotes(nombreArchivo);
			return true;
		}
		else
		{
			//archivo no existe
			return false;
		}
		
	}
	
	public boolean eliminarLoteVencido(String codigoBarras, String codigoLote) 
	{
		return getInventario().eliminarLote(codigoBarras, codigoLote);
	}
	
	public 	Map<String, Double> lotesYcantidades(String codigoBarras)
	{
		//falta implementer
		return null;
	}
	
	
	public boolean existeArchivoUsuarioLote(String nombreArchivo)
	{
		 return new File("datos/"+nombreArchivo+".csv").exists();
		
	}
	
	
	
	public String registrarCompra(Map<String, Double> productosyCantidades, String nombre, String cedula) 
	{
		Inventario inventario = getInventario();
		RegistroCompras registroCompras = getRegistroCompras();
		SistemaPuntos sistemaPuntos = getSistemaPuntos();
		
		boolean sePudieronComprar = true;
		
		
		for (String codigoBarras: productosyCantidades.keySet())
		{
			double cantidad = productosyCantidades.get(codigoBarras);
			sePudieronComprar = sePudieronComprar && inventario.sePuedeComprar(codigoBarras, cantidad);
		} 
		
		if(sePudieronComprar) 
		{
			double precioTotalCompra = 0;
		
			for (String codigoBarras: productosyCantidades.keySet())
			{
				double cantidad = productosyCantidades.get(codigoBarras);
				precioTotalCompra = precioTotalCompra + inventario.comprar(codigoBarras, cantidad);
			}
			
			double puntosCompra = sistemaPuntos.calcularPuntos(precioTotalCompra);
			Cliente cliente = sistemaPuntos.registrarPuntos(cedula, nombre, puntosCompra);
			
			Compra compra = new Compra(precioTotalCompra, cliente, 
					productosyCantidades,  puntosCompra);
			
			registroCompras.guadarCompra(compra);
			
			String factura = compra.generarfactura();
			
			return factura;
		}
		else { return "Las cantidades de estos productos no estaban disponibles";}
	}
	
	
	
	
	public boolean existeArchivo(String nombreArchivo)
	{
		 return new File("archivos/"+nombreArchivo+".csv").exists();
		
	}
	
	
	
	public void leerInfoArchivos(String baseDatos) throws IOException, ParseException 
	{
		
			
		try 
		{
				Inventario inventario = getInventario();
				SistemaPuntos sistemaPuntos = getSistemaPuntos();
				//leer csv y configurar lectura para leer atributos:
				
			
				BufferedReader br = new BufferedReader(new FileReader("datos/"+baseDatos+".csv"));
				
				
				
				String linea = br.readLine();
				linea = br.readLine();
				
				if("puntos".equals(baseDatos))
				{
				while (linea != null) 
				{	
					
					//System.out.println(linea);
					String[] partes = linea.split(",");
					
					String cedula = partes[0];
					String nombre = partes[1];
					double puntos = Double.parseDouble(partes[2]);
					String sexo = partes[3];
					int edad = Integer.parseInt(partes[4]);
					String estadoCivil = partes[5];
					String situacionLaboral = partes[6];
					
					//registrarlo conm metodo de registro
					registrarCliente(nombre, cedula, sexo, edad, estadoCivil, situacionLaboral);
					
					//darle los puntos
					
					sistemaPuntos.getCliente(cedula).sumarPuntos(puntos);
					
					//System.out.println("estoy leyendo linea de csv");
					
					
					
					
					linea = br.readLine();
					
				}
				}
				else if("inventario_lotes".equals(baseDatos))
				{
					SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
					
				while (linea != null) 
				{	
					
					
					//System.out.println(linea);
					String[] partes = linea.split(",");
					
					double cantidadOriginal = Double.parseDouble(partes[0]);
					double cantidadActual = Double.parseDouble(partes[1]);
					Date fechaVencimiento = formatter.parse(partes[2]);
					double costoTotal = Double.parseDouble(partes[3]);
					double precio_publico_unidad = Double.parseDouble(partes[4]);
					String precio_publico_unidad_medida = partes[5];
					Date fecha_lote = formatter.parse(partes[6]);
					String codigo = partes[7];
					String codigoProducto = partes[7];
					
					Producto producto = inventario.getProducto(codigoProducto);
					
					//registrar lote con metodo de registro
					inventario.registrarLote(producto, codigo, cantidadOriginal, fechaVencimiento, costoTotal, precio_publico_unidad, precio_publico_unidad_medida);
					Lote lote = inventario.getLote(codigoProducto, codigo);
					
					
					//ponerle fecha y cantidad actual
					lote.setCantidadActual(cantidadActual);
					lote.setFechaLote(fecha_lote);
					//System.out.println("estoy leyendo linea de csv");
					
					
					
					
					linea = br.readLine();
					
				}
				}
				
				
				else if("inventario_productos".equals(baseDatos))
				{
					
					
				while (linea != null) 
				{	
					
					
					//System.out.println(linea);
					String[] partes = linea.split(",");
					
					String nombre = partes[0];
					String codigoBarras = partes[1];
					String[] categorias = partes[2].split("-");
					String tipoRefrigerado = partes[3];
					double precioActual = Double.parseDouble(partes[4]);
					String precioActualMedida = partes[5];
					double gananciaTotal = Double.parseDouble(partes[6]);
					String empaque = partes[5];
					
					
					//registrar producto con metodo de registro
					inventario.registrarProducto(codigoBarras, precioActual, precioActualMedida, nombre,
							 "null", tipoRefrigerado, empaque);
					Producto producto = inventario.getProducto(codigoBarras);
					
					producto.setGananciaTotal(gananciaTotal);
					
					inventario.agregarCategoriasProducto(codigoBarras, categorias);
					
					
					
					//System.out.println("estoy leyendo linea de csv");
					
					
					
					
					linea = br.readLine();
					
				}
				}
				br.close();
		}	
		catch(FileNotFoundException ex)
		{
			System.out.println("Archivo no encontrado");
		}
	
	}
	
	public void actualizarArchivos(String baseDatos) 
	{
		
		//señalarlo
		//sobreescribirlo
	}
	
	


}
