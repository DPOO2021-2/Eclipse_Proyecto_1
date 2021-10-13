package modelo;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public abstract class Producto {
	private String nombre;
	private String codigoBarras;
	private String categoria;
	private String subcategoria1;
	private String subcategoria2;
	private String tipoRefrigerado;
	private int precioActual;
	
	private int gananciaTotal;

	
	private HashMap<String, Lote> lotes;
	
	
	public Producto(String nombre, String codigoBarras, String categoria, String subcategoria1, String subcategoria2, 
			String tipoRefrigerado, int precioActual, int ganaciaTotal)
	{
		this.nombre = nombre;
		this.codigoBarras = codigoBarras;
		this.categoria = categoria;
		this.subcategoria1 = subcategoria1;
		this.subcategoria2 = subcategoria2;
		this.tipoRefrigerado = tipoRefrigerado;
		this.precioActual = precioActual;
		this.gananciaTotal = ganaciaTotal;
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
	
	
	public HashMap<String, Lote> getLotes()
	{
		return this.lotes;
	}

	public double getGananciaTotal()
	{
		return this.gananciaTotal;
	}
	
	
	public void agregarLote(Lote lote)
	{
		lotes.put(lote.getFecha(), lote);
	}
	

	abstract public double calcularPrecio(double cantidad);
	
	abstract public void comprarProducto(double cantidad);
	
	

}
