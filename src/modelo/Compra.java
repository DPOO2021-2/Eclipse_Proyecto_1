package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Compra {
	
	private double costoFinal;
	
	private Cliente cliente;
	
	private Map<String, Double> productos;
	
	private double puntos;
	
	
	public Compra(Double costoFinal, Cliente cliente, Map<String, Double> productos,
			Double puntos) 
	{
		this.costoFinal = costoFinal;
		this.cliente = cliente;
		this.productos = productos;
		this.puntos = puntos;
	}
	
	
	
	


	public Double getCostoFinal()
	{
		return this.costoFinal;
	}
	
	public Cliente getCliente() 
	{
		return cliente;
	}



	public Map<String, Double> getProductos() 
	{
		return productos;
	}



	public Double getPuntos() 
	{
		return puntos;
	}



	public String generarfactura()
	{
		String factura = "";
		for (String codigoBarras:  productos.keySet())
		{
			factura = factura + codigoBarras + ": " + 
			productos.get(codigoBarras).toString() + ", ";
		}
		factura = factura + " costo total: "+ getCostoFinal().toString();
		return factura;
	}
	
	public String generarFacturaConPuntos(Integer puntos)
	{
		String factura = "";
		for (String codigoBarras:  productos.keySet())
		{
			factura = factura + codigoBarras + ": " + 
			productos.get(codigoBarras).toString() + ", ";
		}
		factura = factura + " subtotal: "+ getCostoFinal().toString();
		Double costoFinalVERDADERO = (getCostoFinal() - puntos);
		
		return factura+"-"+puntos+"="+costoFinalVERDADERO.toString();
	}
	
	
	public String facturaResumida_CSV()
	{
		String factura = "";
		for (String codigoBarras:  productos.keySet())
		{
			factura = factura + codigoBarras + ":" + 
			productos.get(codigoBarras).toString() + "-";
		}

		return factura;
	}

}
