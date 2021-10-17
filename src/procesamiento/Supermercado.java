package procesamiento;

import java.util.Map;

import modelo.Cliente;
import modelo.Compra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class Supermercado 
{
	private Inventario inventario;
	private RegistroCompras registroCompras;
	private SistemaPuntos sistemaPuntos;
	
	private String nombre;
	private Date fecha;
	
	public Supermercado (String nombre)
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
	
	
	public boolean registrarLotes(String nombreArchivo) throws FileNotFoundException, IOException
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
	
	public boolean existeArchivoUsuarioLote(String nombreArchivo)
	{
		 return new File("datos/"+nombreArchivo+".xlsx").exists();
		
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
	
	public boolean eliminarLotesVencidos(String codigoBarras, String codigoLote) 
	{
		return inventario.eliminarLote(codigoBarras, codigoLote);
	}

	
	


}
