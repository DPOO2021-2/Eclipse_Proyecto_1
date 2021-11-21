package procesamiento;

import java.util.Map;

import modelo.Cliente;
import modelo.ClienteRegistrado;
import modelo.Compra;
import modelo.Lote;
import modelo.Producto;

import modelo.TransformadorFechas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


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
		leerInfoArchivos("compras");
		
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
	
	public boolean registrarCliente(String nombre, String cedula, String sexo, String edad, String estadoCivil, String situacionLaboral)
	{
		
		
		return getSistemaPuntos().registrarCliente(nombre, cedula, sexo, edad, estadoCivil, situacionLaboral);
		//retorna false si el cliente ya estaba registrado (no lo registra de nuevo)
		//ignorar el comentario arriba de este
		
		
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
	
	public 	Map<String, Double> lotesYcantidades(String codigoBarras) throws NullPointerException
	{
	
		
		try
		{
			return getInventario().lotesYcantidades(codigoBarras);
		}
		catch(NullPointerException ne)
		{
			throw ne;
		}
	}
	
	public boolean setImagenProducto(String codigoBarras, String nombreImagen)
	{
		Producto producto = inventario.getProducto(codigoBarras);
		if (producto==null)
		{
			return false;
		}
		else
		{
			producto.setImagen(nombreImagen);
			return true;
		}
	}
	
	public String getImagenProducto(String codigoBarras)
	{
		Producto producto = inventario.getProducto(codigoBarras);
		return producto.getImagen();
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
			
			registroCompras.guadarCompra(cedula, compra);
			
			String factura = compra.generarfactura();
			
			return factura;
		}
		else 
		{ 
			return "no";
		}
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

				RegistroCompras registroCompras = getRegistroCompras();
				
				//leer csv y configurar lectura para leer atributos:
				
			
				BufferedReader br = new BufferedReader(new FileReader("archivos/"+baseDatos+".csv"));
				
				
				
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
//					double puntos = Double.parseDouble(partes[2]);
					double puntos = 0;
					String sexo = partes[2];
					String edad = partes[3];
					String estadoCivil = partes[4];
					String situacionLaboral = partes[5];
					
					//registrarlo conm metodo de registro
					registrarCliente(nombre, cedula, sexo, edad, estadoCivil, situacionLaboral);
					
					//darle los puntos
					
					sistemaPuntos.getCliente(cedula).sumarPuntos(puntos);
					
//					System.out.println("acabo de registrar cliente");
					
					
					
					
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
					String codigoProducto = partes[8];
					
					
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
					String[] categorias = partes[2].split("|");
					String tipoRefrigerado = partes[3];
					double precioActual = Double.parseDouble(partes[4]);
					String precioActualMedida = partes[5];
					double gananciaTotal = Double.parseDouble(partes[6]);
					String empaque = partes[7];
					
					
					//registrar producto con metodo de registro
					Producto producto = inventario.registrarProducto(codigoBarras, precioActual, precioActualMedida, nombre,
							 null, tipoRefrigerado, empaque);
					
					
					producto.setGananciaTotal(gananciaTotal);
					
					inventario.agregarCategoriasProducto(codigoBarras, categorias);
					
					
					
					//System.out.println("estoy leyendo linea de csv");
					
					
					
					
					linea = br.readLine();
					
				}
				}
				else if("compras".equals(baseDatos))
				{
					
					
					
				while (linea != null) 
				{	
					
					
					//System.out.println(linea);
					String[] partes = linea.split(",");
					
					double precioTotalCompra = Double.parseDouble(partes[0]);
					String cedulaCliente = partes[1];
					String nombreCliente = partes[2];
					String[] productos = partes[3].split("-");
					
					Map<String, Double> productosyCantidades = new HashMap<String, Double>();
					for (String str: productos)
					{
						String[] pareja = str.split(":");
						productosyCantidades.put(pareja[0], Double.parseDouble(pareja[1]));
					}
					
					double puntosCompra = sistemaPuntos.calcularPuntos(precioTotalCompra);
					Cliente cliente = sistemaPuntos.registrarPuntos(cedulaCliente, nombreCliente, puntosCompra);
					
					Compra compra = new Compra(precioTotalCompra, cliente, 
							productosyCantidades,  puntosCompra);
					
					registroCompras.guadarCompra(cedulaCliente, compra);
					
					
					
					
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
	
	public void actualizarArchivos(String baseDatos) throws IOException 
	{
		Inventario inventario = getInventario();
		SistemaPuntos sistemaPuntos = getSistemaPuntos();
		RegistroCompras registroCompras = getRegistroCompras();
		
		FileWriter csvWriter = new FileWriter("archivos/"+baseDatos+".csv");
		
		if("puntos".equals(baseDatos) && (!sistemaPuntos.vacio()))
		{		
				csvWriter.append("cedula");
				csvWriter.append(",");
				csvWriter.append("nombre");
				csvWriter.append(",");
				/*
				csvWriter.append("puntos");
				csvWriter.append(",");
				*/
				csvWriter.append("sexo");
				csvWriter.append(",");
				csvWriter.append("edad");
				csvWriter.append(",");
				csvWriter.append("estadoCivil");
				csvWriter.append(",");
				csvWriter.append("situacionLaboral");
				csvWriter.append("\n");
				
				for (ClienteRegistrado cliente :sistemaPuntos.getClientes().values())
				{
					
					String cedula = cliente.getCedula();
					String nombre = cliente.getNombre();
	//				String puntos = cliente.getPuntos().toString();
					String sexo = cliente.getSexo();
					String edad = cliente.getEdad().toString();
					String estadoCivil = cliente.getEstadoCivil();
					String situacionLaboral = cliente.getSituacionLaboral();
					
					
					csvWriter.append(cedula);
					csvWriter.append(",");
					csvWriter.append(nombre);
					csvWriter.append(",");
					/*
					csvWriter.append(puntos);
					csvWriter.append(",");
					*/
					csvWriter.append(sexo);
					csvWriter.append(",");
					csvWriter.append(edad);
					csvWriter.append(",");
					csvWriter.append(estadoCivil);
					csvWriter.append(",");
					csvWriter.append(situacionLaboral);
					csvWriter.append("\n");
				}
				
				
			
		}
		else if("inventario_productos".equals(baseDatos) && (!inventario.vacio()))
		{		
				csvWriter.append("nombre");
				csvWriter.append(",");
				csvWriter.append("codigoBarras");
				csvWriter.append(",");
				csvWriter.append("categorias");
				csvWriter.append(",");
				csvWriter.append("tipoRefrigerado");
				csvWriter.append(",");
				csvWriter.append("precioActual");
				csvWriter.append(",");
				csvWriter.append("precioActualMedida");
				csvWriter.append(",");
				csvWriter.append("gananciaTotal");
				csvWriter.append(",");
				csvWriter.append("empaque");
				csvWriter.append("\n");
				
				for (Producto producto :inventario.getProductos().values())
				{
					
				
					String nombre = producto.getNombre();
					String codigoBarras = producto.getCodigoBarras();
					String categorias = producto.getStringCategorias();
					
					String tipoRefrigerado = producto.getTipoRefrigerado();
					String precioActual = producto.getPrecioActual().toString();
					String precioActualMedida = producto.getPrecioActualMedida();
					String gananciaTotal = producto.getGananciaTotal().toString();
					String empaque = producto.getClass().toString().split("Producto")[1];
//					System.out.println(empaque);
					
					
					
					csvWriter.append(nombre);
					csvWriter.append(",");
					csvWriter.append(codigoBarras);
					csvWriter.append(",");
					csvWriter.append(categorias);
					csvWriter.append(",");
					csvWriter.append(tipoRefrigerado);
					csvWriter.append(",");
					csvWriter.append(precioActual);
					csvWriter.append(",");
					csvWriter.append(precioActualMedida);
					csvWriter.append(",");
					csvWriter.append(gananciaTotal);
					csvWriter.append(",");
					csvWriter.append(empaque);
					
					csvWriter.append("\n");
					
				}
				
				
			
		}
		else if("inventario_lotes".equals(baseDatos) && (!inventario.vacio()))
		{		
				csvWriter.append("cantidadOriginal");
				csvWriter.append(",");
				csvWriter.append("cantidadActual");
				csvWriter.append(",");
				csvWriter.append("FechaVencimiento");
				csvWriter.append(",");
				csvWriter.append("costoTotal");
				csvWriter.append(",");
				csvWriter.append("precio_publico_unidad");
				csvWriter.append(",");
				csvWriter.append("precio_publico_unidad_medida");
				csvWriter.append(",");
				csvWriter.append("fechaLote");
				csvWriter.append(",");
				csvWriter.append("codigo");
				csvWriter.append(",");
				csvWriter.append("codigoProducto");
				csvWriter.append("\n");
				
				for (Producto producto :inventario.getProductos().values())
				{
					
				
					for (Lote lote: producto.getLotes().values())
					{
						String cantidadOriginal = lote.getCantidadOriginal().toString();
						String cantidadActual = lote.getCantidadActual().toString();
						
						String FechaVencimiento = TransformadorFechas.fechaTransf(lote.getFechaVencimiento());
						String costoTotal = lote.getCostoTotal().toString();
						String precio_publico_unidad = lote.getPrecio_publico_unidad().toString();
						String precio_publico_unidad_medida = lote.getPrecio_publico_unidad_medida();
						String fechaLote = TransformadorFechas.fechaTransf(lote.getFecha());
						String codigo = lote.getCodigo();
						String codigoProducto = producto.getCodigoBarras();
						
						
						
						csvWriter.append(cantidadOriginal);
						csvWriter.append(",");
						csvWriter.append(cantidadActual);
						csvWriter.append(",");
						csvWriter.append(FechaVencimiento);
						csvWriter.append(",");
						csvWriter.append(costoTotal);
						csvWriter.append(",");
						csvWriter.append(precio_publico_unidad);
						csvWriter.append(",");
						csvWriter.append(precio_publico_unidad_medida);
						csvWriter.append(",");
						csvWriter.append(fechaLote);
						csvWriter.append(",");
						csvWriter.append(codigo);
						csvWriter.append(",");
						csvWriter.append(codigoProducto);
						csvWriter.append("\n");
					}
					
				}
				
				
			
		}
		else if("compras".equals(baseDatos) && (!registroCompras.vacio()) )
		{
			
				csvWriter.append("costoFinal");
				csvWriter.append(",");
				csvWriter.append("cedulaCliente");
				csvWriter.append(",");
				csvWriter.append("nombreCliente");
				csvWriter.append(",");
				csvWriter.append("productos");
				csvWriter.append("\n");
				
				for (ArrayList<Compra> lista: registroCompras.getCompras().values())
				{	
					for (Compra compra: lista)
					{
						String costoFinal = compra.getCostoFinal().toString();
						
						Cliente cliente = compra.getCliente();
						
						String cedulaCliente = cliente.getCedula();
						String nombreCliente = cliente.getNombre();
						
						String productos = compra.facturaResumida_CSV();

						csvWriter.append(costoFinal);
						csvWriter.append(",");
						csvWriter.append(cedulaCliente);
						csvWriter.append(",");
						csvWriter.append(nombreCliente);
						csvWriter.append(",");
						csvWriter.append(productos);
						csvWriter.append("\n");
					}
					
				}	
				
				
				
			
		}
		
			

			csvWriter.flush();
			csvWriter.close();
		
	}
	
	
	public ArrayList<Double> puntosComprasCliente(String cedula) throws NullPointerException
	{
		try 
		{
			ArrayList<Double> retorno = new ArrayList<Double>();
			for (Compra compra: getRegistroCompras().getCompras().get(cedula))
			{
				retorno.add(compra.getPuntos());
			}
			return retorno;
		}
		catch(NullPointerException npe)
		{
			throw npe;
		}
	}
	


}
