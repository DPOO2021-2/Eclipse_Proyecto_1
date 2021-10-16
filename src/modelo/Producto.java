package modelo;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class Producto {
	private String nombre;
	private String codigoBarras;
	private String categoria;
	private String subcategoria1;
	private String subcategoria2;
	private String tipoRefrigerado;
	private int precioActual;
	
	private double gananciaTotal;

	
	private LinkedHashMap<String, Lote> lotes;
	
	
	public Producto(String nombre, String codigoBarras, String categoria, String subcategoria1, String subcategoria2, 
			String tipoRefrigerado, int precioActual)
	{
		this.nombre = nombre;
		this.codigoBarras = codigoBarras;
		this.categoria = categoria;
		this.subcategoria1 = subcategoria1;
		this.subcategoria2 = subcategoria2;
		this.tipoRefrigerado = tipoRefrigerado;
		this.precioActual = precioActual;
		this.gananciaTotal = 0;
		this.lotes = new LinkedHashMap<String, Lote>();
	}
	
	public int getPrecioActual()
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
		lotes.put(lote.getFecha(), lote);
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
	

	//antes de llamar este metodo asegurese de que 
		//cantidad<cantidadTotal()
	public double calcularPrecio(double cantidad)
	{
		return getPrecioActual()*cantidad;
	}
	


	public boolean sePuedeComprar(double cantidad)
	{
		return cantidadTotal() > cantidad;
	}
	
	abstract public double comprarProducto(double cantidad);
	
	

}
