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
	
	private double subTotal;
	
	
	public Compra(Double subTotal, Cliente cliente, Map<String, Double> productos,
			Double puntos) 
	{
		
		this.costoFinal = subTotal;
		this.subTotal = subTotal;
		this.cliente = cliente;
		this.productos = productos;
		this.puntos = puntos;
		this.promocionesAplicadas = new ArrayList<Promocion>();
		
	}
	
	
	public void setCostoFinal(Double costo)
	{
		this.costoFinal = costo;
	}
	


	public Double getCostoFinal()
	{
		return this.costoFinal;
	}
	
	public Double getSubtotal()
	{
		return this.subTotal;
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

	public void setPuntosFinal(Double puntos)
	{
		this.puntos=puntos;
	}


	public String generarfactura()
	{
		String factura = "";
		for (String codigoBarras:  productos.keySet())
		{
			factura = factura + codigoBarras + ": " + 
			productos.get(codigoBarras).toString() + ", ";
		}
		factura = factura + " subtotal : "+ getSubtotal().toString();
		
		factura = factura + "\n"+"promociones aplicadas: ";
		
		Integer contador = 0;
		for (Promocion promocion: promocionesAplicadas)
		{
			contador = contador+1;
			factura = factura + "\n"+contador.toString()+promocion.toString();
		}
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
		Double costoFinalVERDADERO = (getSubtotal() - (puntos * 15));
		
		Double puntosNuevos = puntosAntiguos - puntos;
		
		factura = factura+" - "+(puntos * 15)+" = "+costoFinalVERDADERO.toString()+
				"Antes tenias: "+puntosAntiguos.toString()+" y ahora te quedan: "+puntosNuevos.toString();

		factura = factura + "\n"+" promociones aplicadas: ";
		
		Integer contador = 0;
		for (Promocion promocion: promocionesAplicadas)
		{
			contador = contador+1;
			factura = factura + "\n"+contador.toString()+promocion.toString();
		}
		return factura;
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
