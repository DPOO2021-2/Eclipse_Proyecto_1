package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Compra {
	
	private double costoFinal;
	
	private Cliente cliente;
	
	private Map<String, Double> productos;
	
	private double puntos;
	
	private ArrayList<Promocion> promocionesAplicadas;
	
	
	public Compra(Double costoFinal, Cliente cliente, Map<String, Double> productos,
			Double puntos) 
	{
		
		this.costoFinal = costoFinal;
		this.cliente = cliente;
		this.productos = productos;
		this.puntos = puntos;
		this.promocionesAplicadas = new ArrayList<Promocion>();
		
	}
	
	
	public void setCostoFinal(Double costo)
	{
		this.costoFinal = costo;
	}
	
	
	public void setPuntosFinal(Double puntosF)
	{
		this.puntos = puntosF;
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
	
	public String generarFacturaConPuntos(Integer puntos, Double puntosAntiguos)
	{
		String factura = "";
		for (String codigoBarras:  productos.keySet())
		{
			factura = factura + codigoBarras + ": " + 
			productos.get(codigoBarras).toString() + ", ";
		}
		factura = factura + " subtotal: "+ getCostoFinal().toString();
		Double costoFinalVERDADERO = (getCostoFinal() - (puntos * 15));
		
		Double puntosNuevos = puntosAntiguos - puntos;
		
		return factura+" - "+(puntos * 15)+" = "+costoFinalVERDADERO.toString()+
				"Antes tenias: "+puntosAntiguos.toString()+" y ahora te quedan: "+puntosNuevos.toString();
	}
	
	
	public String facturaResumida_CSV()
	{
		String factura = "";
		for (String codigoBarras:  productos.keySet())
		{
			factura = factura + codigoBarras + ":" + 
			productos.get(codigoBarras).toString() + "-";
		}
		
		factura = factura.substring(0, factura.length() - 1);

		return factura;
	}
	
	public void registrarPromocion(Promocion promocion)
	{
		this.promocionesAplicadas.add(promocion);
	}

}
