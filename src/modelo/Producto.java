package modelo;

import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import java.util.Date;

public abstract class Producto 
{
	
	
	private String nombre;
	private String codigoBarras;
	
	private ArrayList<String> categorias;
	
	private String tipoRefrigerado;
	private double precioActual;
	
	private double gananciaTotal;

	//las llaves seran codigos que deberan venir en el archivo csv junto el codigo de barras del producto de la forma
	//xxxx-c  donde xxxx es el cdoigo de barras del producto y c el codigo del lote
	private LinkedHashMap<String, Lote> lotes;
	
	
	public Producto(String nombre, String codigoBarras, String tipoRefrigerado, double precioActual)
	{
		this.nombre = nombre;
		this.codigoBarras = codigoBarras;
		this.categorias = new ArrayList<String>();
		this.tipoRefrigerado = tipoRefrigerado;
		this.precioActual = precioActual;
		this.gananciaTotal = 0;
		this.lotes = new LinkedHashMap<String, Lote>();
	}
	
	public double getPrecioActual()
	{return this.precioActual;}
	
	public String getNombre()
	{return this.nombre;}
	
	public String getCodigoBarras()
	{return this.codigoBarras;}
	
	public double getCostoTotal()
	{
		double costoTotal = 0;
		for (Lote lote: getLotes().values())
		{
			costoTotal = costoTotal + lote.getCostoLote();
		}
		return costoTotal;
	}
	
	
	public LinkedHashMap<String, Lote> getLotes()
	{
		return this.lotes;
	}

	public double getGananciaTotal()
	{
		return this.gananciaTotal;
	}
	
	public void setGananciaTotal(double ganancia)
	{
		this.gananciaTotal = ganancia;
	}
	
	
	public void agregarLote(Lote lote)
	{
		lotes.put(lote.getCodigo(), lote);
	}
	
	public double cantidadTotal()
	{
		double cantidad = 0;
		for (Lote lote: getLotes().values())
		{
			cantidad = cantidad + lote.getCantidadActual();
		}
		return cantidad;
	}
	

	//antes de llamar este metodo asegurese de llamar al metodo sePuedeComprar
	public double calcularPrecio(double cantidad)
	{
		return getPrecioActual()*cantidad;
	}
	


	public boolean sePuedeComprar(double cantidad)
	{
		return cantidadTotal() > cantidad;
	}
	
	//antes de llamar este metodo asegurese de llamar al metodo sePuedeComprar
	abstract public double comprarProducto(double cantidad);

	public void agregarCategoria(String categoria)
	{
		ArrayList<String> categorias = getCategorias();
		
		if(!(categorias.contains(categoria)))
		{
			categorias.add(categoria);
		}
		
	}

	public ArrayList<String> getCategorias()
	{
		return this.categorias;
	}
	
	

}
