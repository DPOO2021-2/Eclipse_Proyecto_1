package procesamiento;

import java.util.Map;
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
	
	
	
	public void registrarCompra(Map<String, Double> productosyCantidades, String nombre, String cedula) 
	{
		boolean sePudieronComprar = true;
		for (String codigoBarras: productosyCantidades.keySet())
		{
			double cantidad = productosyCantidades.get(codigoBarras);
			sePudieronComprar = sePudieronComprar && inventario.sePuedeComprar(codigoBarras, cantidad);
		} 
		
		if(sePudieronComprar) 
		{
		
		for (String codigoBarras: productosyCantidades.keySet())
		{
			double cantidad = productosyCantidades.get(codigoBarras);
			inventario.comprar(codigoBarras, cantidad);
		}
		
		}
	}

}
