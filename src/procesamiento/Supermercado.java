package procesamiento;

import java.util.Map;

import modelo.Cliente;
import modelo.Compra;

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
		this.nombre = nombre;
		this.fecha = new Date();
		
		this.inventario = new Inventario();
		this.registroCompras = new RegistroCompras();
		this.sistemaPuntos = new SistemaPuntos();
		
	}
	
	
	
	public String registrarCompra(Map<String, Double> productosyCantidades, String nombre, String cedula) 
	{
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

}
