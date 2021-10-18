package procesamiento;

import java.util.Map;

import modelo.Cliente;
import modelo.Compra;

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
		 * 2. req func: registrar lotes y productos (se registran por un csv, input: String nombreArchivo)
		 * 3. req func: eliminar lotes vencidos (inputs=[cdoigoBarras, codigoLote])
		 * 
		 * 
		 *  
		 * 5. Hacer ambas consolas
		 * 
		 * 6. Hacer archivos: guardar y leer
		 * 
		 */
		this.nombre = nombre;
		this.fecha = new Date();
		
		this.inventario = new Inventario();
		this.registroCompras = new RegistroCompras();
		this.sistemaPuntos = new SistemaPuntos();
		
		//aqui se debe ejecutar el metodo de cargar nuestros archivos
		
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
					
					getSistemaPuntos().getCliente(cedula).sumarPuntos(puntos);
					
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
			//señalarlo
			//leerlo
	
	}
	
	public void actualizarArchivos(String baseDatos) 
	{
		
		//señalarlo
		//sobreescribirlo
	}
	
	


}
